package com.example.oving6

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ChatScreenWithViewModel(
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = viewModel(),
) {
    val isServer by viewModel.isServer.collectAsStateWithLifecycle()
    val messages by viewModel.messages.collectAsStateWithLifecycle()
    var draftMessage by rememberSaveable { mutableStateOf("") }

    ChatScreen(
        isServer = isServer,
        messages = messages,
        draftMessage = draftMessage,
        onDraftMessageChanged = { draftMessage = it },
        onStartServer = { viewModel.startAsServer() },
        onStartClient = { viewModel.startAsClient() },
        onSendMessage = { text ->
            if (text.isNotBlank()) {
                viewModel.send(text)
                draftMessage = ""
            }
        },
        modifier = modifier
    )
}