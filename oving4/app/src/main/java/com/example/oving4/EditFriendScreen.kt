package com.example.oving4

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun EditFriendScreen(
    index: Int,
    viewModel: FriendViewModel,
    onDone: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val name by viewModel.name.collectAsStateWithLifecycle()
    val birthday by viewModel.birthday.collectAsStateWithLifecycle()
    AddFriend(
        name = name,
        onNameChange = { viewModel.changeName(it) },
        birthday = birthday,
        onBirthdayChange = { viewModel.changeBirthday(it) },
        onAdd = {
            viewModel.updateFriendByIndex(index)
            onDone()
        },
        modifier = modifier,
    )
}
