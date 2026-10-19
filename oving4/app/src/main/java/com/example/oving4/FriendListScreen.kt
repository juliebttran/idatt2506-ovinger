package com.example.oving4

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.oving4.R

@Composable
fun FriendListScreen(
    friends: List<Friend>,
    onFriendsClick: (Friend) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(16.dp)) {
        Button(
            onClick = onAddClick,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(text = stringResource(R.string.add))
        }
        FriendList(
            friends = friends,
            onFriendsClick = onFriendsClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FriendListScreenPreview() {
    FriendListScreen(friends = sampleFriends, onFriendsClick = {}, onAddClick = {})
}