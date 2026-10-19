package com.example.oving4

import android.R.attr.name
import android.service.notification.Condition.newId
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.res.stringResource

@Composable
fun FriendScreen(
    friends: List<Friend>,
    newName: String,
    onNameChange: (String) -> Unit,
    newBirthday: String,
    onBirthdayChange: (String) -> Unit,
    onAddFriend: () -> Unit,
    modifier: Modifier = Modifier,
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
            birthday = newBirthday,
            onBirthdayChange = onBirthdayChange,
            onAdd = onAddFriend,
        )
    }
}

@Preview(showBackground = true, heightDp = 640)
@Composable
fun FriendScreenPreview() {
    FriendScreen(
        friends = sampleFriends,
        newName = "",
        newBirthday = "",
        onNameChange = {},
        onBirthdayChange = {},
        onAddFriend = {},
    )
}
