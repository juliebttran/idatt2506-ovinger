package com.example.oving6

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    onDraftMessageChanged: (String) -> Unit,
    onStartServer: () -> Unit,
    onStartClient: () -> Unit,
    onSendMessage: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {ChatTopAppBar(isServer = isServer)
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (isServer == null) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(R.string.choose_role), style = MaterialTheme.typography.headlineMedium)
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
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(messages) { message ->
                            Text(
                                text = (if (message.fromMe) stringResource(R.string.from_me) else stringResource(R.string.from_other)) + message.text,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

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
        onDraftMessageChanged = {},
        onStartServer = {},
        onStartClient = {},
        onSendMessage = {}
    )
}