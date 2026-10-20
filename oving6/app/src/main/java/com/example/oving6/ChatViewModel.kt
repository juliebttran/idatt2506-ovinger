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

class ChatViewModel : ViewModel() {
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

    private var connection: Connection? = null

    private val server = ChatServer(PORT)
    private val client = ChatClient(HOST, PORT)

    private fun add(message: Message) {
        _messages.value += message
    }

    private suspend fun listen(open: Connection) {
        try {
            while (true) {
                val line = open.receive() ?: break
                add(Message(fromMe = false, text = line))
            }
        }catch (e: IOException) {

        }
        _closed.value = true
        _clientCount.value = 0
    }

    override fun onCleared() {
        connection?.close()
        server.close()
    }
    fun startAsServer() = start(asServer = true)

    fun startAsClient() = start(asServer = false)

    private fun start(asServer: Boolean) {
        if (_isServer.value != null) return

        _isServer.value = asServer
        viewModelScope.launch {
            try {
                val socket = if (asServer) server.awaitClient() else client.connect()
                val open = Connection(socket)
                connection = open
                _clientCount.value = 1
                listen(open)
            } catch (e: IOException) {
                _error.value = e.toString()
                _clientCount.value = 0
            }
        }
    }

    fun send(text: String) {
        val open = connection ?: return
        viewModelScope.launch {
            try {
                open.send(text)
                add(Message(fromMe = true, text = text))
            } catch (e: IOException) {

            }
        }
    }
}