package com.example.oving6

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopAppBar(
    isServer: Boolean?,
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
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        modifier = modifier
    )
}