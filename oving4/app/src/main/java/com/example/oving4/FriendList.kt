package com.example.oving4

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FriendList(
    friends: List<Friend>,
    onFriendsClick: (Friend) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 8.dp),
        modifier = modifier,
    ) {
        items(friends){friend ->
            FriendListCard(
                friend=friend,
                modifier = Modifier.clickable { onFriendsClick(friend) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FriendListPreview() {
    FriendList(friends = sampleFriends, onFriendsClick = {})
}