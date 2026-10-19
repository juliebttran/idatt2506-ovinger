package com.example.oving3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FriendScreen(
    friends: List<Friend>,
    newName: String,
    onNameChange: (String) -> Unit,
    newDate: String,
    onDateChange: (String) -> Unit,
    onAddFriend: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(friends) { friend ->
                FriendListCard(friend = friend)
            }
        }
        AddFriend(
            name = newName,
            onNameChange = onNameChange,
            date = newDate,
            onDateChange = onDateChange,
            onAdd = onAddFriend,
        )
    }
}

@Preview(showBackground = true, heightDp = 600)
@Composable
fun FriendScreenPreview() {
    FriendScreen(
        friends = sampleFriends,
        newName = "",
        onNameChange = {},
        newDate = "",
        onDateChange = {},
        onAddFriend = {},
    )
}