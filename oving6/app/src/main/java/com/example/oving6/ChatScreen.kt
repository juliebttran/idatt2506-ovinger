package com.example.oving6

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen(
    isServer: Boolean?,
    messages: List<Message>,
    draftMessage: String,
    clientCount: Int = 0,
    onDraftMessageChanged: (String) -> Unit,
    onStartServer: () -> Unit,
    onStartClient: () -> Unit,
    onSendMessage: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Scaffold(
        topBar = {
            ChatTopAppBar(
                isServer = isServer,
                clientCount = clientCount
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            if (isServer == null) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.choose_role),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onStartServer) {
                        Text(text = stringResource(R.string.server_role))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = onStartClient) {
                        Text(text = stringResource(R.string.client_role))
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(messages) { message ->
                            val alignment = if (message.fromMe) Alignment.End else Alignment.Start
                            val prefix = if (message.fromMe) stringResource(R.string.from_me) else stringResource(R.string.from_other)

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = alignment
                            ) {
                                Surface(
                                    shape = MaterialTheme.shapes.medium,
                                    color = if (message.fromMe) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "$prefix ${message.text}",
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }
                        }
                    }

                    if (isServer == false) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                value = draftMessage,
                                onValueChange = onDraftMessageChanged,
                                singleLine = true,
                                modifier = Modifier.weight(1f),
                                placeholder = { Text(text = stringResource(R.string.draft_placeholder)) }
                            )
                            Button(onClick = { onSendMessage(draftMessage) }) {
                                Text(text = stringResource(R.string.send))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen(
        isServer = false,
        messages = listOf(
            Message(fromMe = true, text = "Hei!"),
            Message(fromMe = false, text = "Hallo! Hvordan går det?")
        ),
        draftMessage = "Bra!",
        clientCount = 1,
        onDraftMessageChanged = {},
        onStartServer = {},
        onStartClient = {},
        onSendMessage = {}
    )
}