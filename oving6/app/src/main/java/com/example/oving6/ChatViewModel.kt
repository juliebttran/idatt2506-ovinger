package com.example.oving6

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

const val PORT = 12345
const val HOST = "10.0.2.2"

private const val SYSTEM_COUNT_PREFIX = "__CLIENT_COUNT__:"

class ChatViewModel : ViewModel() {
    private val server = ChatServer(PORT)
    private val client = ChatClient(HOST, PORT)
    private val connections = mutableListOf<Connection>()
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()
    private val _closed = MutableStateFlow<Boolean>(false)
    val closed: StateFlow<Boolean> = _closed.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    private val _isServer = MutableStateFlow<Boolean?>(null)
    val isServer: StateFlow<Boolean?> = _isServer.asStateFlow()
    private val _clientCount = MutableStateFlow(0)
    val clientCount: StateFlow<Int> = _clientCount.asStateFlow()
    fun startAsServer() = start(asServer = true)
    fun startAsClient() = start(asServer = false)
    private fun start(asServer: Boolean) {
        if (_isServer.value != null) return
        _isServer.value = asServer

        viewModelScope.launch {
            if (asServer) {
                try {
                    while (true) {
                        val socket = server.awaitClient()
                        val connection = Connection(socket)
                        handleConnection(connection)
                    }
                } catch (e: IOException) {
                }
            } else {
                try {
                    val socket = client.connect()
                    val connection = Connection(socket)
                    handleConnection(connection)
                } catch (e: IOException) {
                }
            }
        }
    }

    private fun handleConnection(connection: Connection) {
        synchronized(connections) {
            connections.add(connection)
            updateClientCount(connections.size)
        }

        viewModelScope.launch {
            try {
                while (true) {
                    val line = connection.receive() ?: break

                    if (line.startsWith(SYSTEM_COUNT_PREFIX)) {
                        val count = line.substringAfter(SYSTEM_COUNT_PREFIX).toIntOrNull()
                        if (count != null) {
                            _clientCount.value = count
                        }
                    } else {
                        add(Message(fromMe = false, text = line))

                        if (_isServer.value == true) {
                            sendToOthers(line, sender = connection)
                        }
                    }
                }
            } catch (e: IOException) {
            } finally {
                synchronized(connections) {
                    connections.remove(connection)
                    updateClientCount(connections.size)
                }
                connection.close()
            }
        }
    }

    private fun updateClientCount(count: Int) {
        _clientCount.value = count
        if (_isServer.value == true) {
            broadcastSystemMessage("$SYSTEM_COUNT_PREFIX$count")
        }
    }

    private fun broadcastSystemMessage(sysMsg: String) {
        viewModelScope.launch {
            val currentConnections = synchronized(connections) { connections.toList() }
            for (conn in currentConnections) {
                try {
                    conn.send(sysMsg)
                } catch (e: IOException) {
                }
            }
        }
    }

    private suspend fun sendToOthers(line: String, sender: Connection) {
        val currentConnections = synchronized(connections) { connections.toList() }
        for (conn in currentConnections) {
            if (conn === sender) continue
            try {
                conn.send(line)
            } catch (e: IOException) {
            }
        }
    }

    fun send(text: String) {
        viewModelScope.launch {
            val currentConnections = synchronized(connections) { connections.toList() }
            for (conn in currentConnections) {
                try {
                    conn.send(text)
                } catch (e: IOException) {
                }
            }
            add(Message(fromMe = true, text = text))
        }
    }

    private fun add(message: Message) {
        _messages.value += message
    }

    override fun onCleared() {
        server.close()
        client.close()
        val currentConnections = synchronized(connections) { connections.toList() }
        for (conn in currentConnections) {
            conn.close()
        }
    }
}