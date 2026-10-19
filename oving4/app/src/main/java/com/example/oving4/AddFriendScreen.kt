package com.example.oving4

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AddFriendScreen(
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
            viewModel.addFriend()
            onDone()
        },
        modifier = modifier,
    )
}
