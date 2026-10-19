package com.example.oving3

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


@Composable
fun AddFriend(
    name: String,
    onNameChange: (String) -> Unit,
    date: String,
    onDateChange: (String) -> Unit,
    onAdd: () -> Unit,
    modifier: Modifier = Modifier,
){
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(16.dp),
    ){
        NameField(value = name, onValueChange = onNameChange)
        DateField(value = date, onValueChange = onDateChange)
        Button(onClick = onAdd) {
            Text(text= stringResource(R.string.add))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddFriendPreview() {
    AddFriend(name = "Julie", onNameChange = {}, date = "22.08.02", onDateChange = {}, onAdd = {})
}
