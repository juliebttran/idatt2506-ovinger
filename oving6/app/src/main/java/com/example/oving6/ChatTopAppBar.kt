package com.example.oving6

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopAppBar(
    isServer: Boolean?,
    clientCount: Int = 0,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = when (isServer) {
                    true -> stringResource(R.string.server_title)
                    false -> stringResource(R.string.client_title)
                    null -> stringResource(R.string.choose_role)
                }
            )
        },
        actions = {
            if (isServer != null) {
                Text(
                    text = stringResource(R.string.connected_clients, clientCount),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        modifier = modifier
    )
}