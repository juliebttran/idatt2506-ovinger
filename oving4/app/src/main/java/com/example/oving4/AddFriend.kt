package com.example.oving4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
fun AddFriend(
    name: String,
    onNameChange: (String) -> Unit,
    birthday: String,
    onBirthdayChange: (String) -> Unit,
    onAdd: () -> Unit,
    modifier: Modifier = Modifier,
){
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(16.dp),
    ){
        NameField(value = name, onValueChange = onNameChange)
        BirthdayField(value = birthday, onValueChange = onBirthdayChange)
        Button(onClick = onAdd) {
            Text(text= stringResource(R.string.save))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddFriendPreview() {
    AddFriend(name = "Julie", onNameChange = {}, birthday = "22.08.02", onBirthdayChange = {}, onAdd = {})
}
