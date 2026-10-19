package com.example.oving4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.oving4.R

@Composable
fun FriendDetailScreen(
    friend: Friend,
    onBack: () -> Unit,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Row(modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = onBack) {
                Text(text = stringResource(R.string.back_to_list))
            }
            Button(onClick = onEdit) {
                Text(text = stringResource(R.string.edit))
            }
        }

        Text(text = friend.name, style =
            MaterialTheme.typography.headlineMedium)

        Text(text = friend.birthday, style =
            MaterialTheme.typography.bodyLarge)
        FriendActions(name = friend.name, modifier = Modifier.padding(top
        = 16.dp))


    }
}

@Preview(showBackground = true)
@Composable
fun FriendDetailScreenPreview() {
    FriendDetailScreen(
        friend = Friend(0, name = "Kari", birthday = "01.05.1998"),
        onBack = {},
        onEdit = {},
        )
}