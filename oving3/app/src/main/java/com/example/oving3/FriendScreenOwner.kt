package com.example.oving3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun FriendScreenOwner(modifier: Modifier = Modifier) {
    var friends by remember { mutableStateOf(friends) }
    var newName by rememberSaveable { mutableStateOf("") }
    var newDate by rememberSaveable { mutableStateOf("") }

    FriendScreen(
        friends = friends,
        newName = newName,
        onNameChange = {newName = it },
        newDate = newDate,
        onDateChange = {newDate = it},
        onAddFriend = {
            friends = friends + Friend(name = newName, date = newDate)
            newName = ""
            newDate = ""
        },
        modifier = modifier,
    )
}
