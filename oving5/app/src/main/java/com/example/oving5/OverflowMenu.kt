package com.example.oving5

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
@Composable
fun OverflowMenu(
    onNewGame: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        IconButton(onClick = {expanded = true}) {
            val description = stringResource(R.string.more_options)
            Icon(painterResource(R.drawable.more_vert), description)
        }
        DropdownMenu(expanded, onDismissRequest = {expanded=false}) {
            DropdownMenuItem(
                text = {Text(stringResource(R.string.new_game))},
                onClick = {expanded = false; onNewGame()},
            )
        }
    }
}