package com.example.oving3

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NameField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(R.string.friend_name)) },
        modifier = modifier.fillMaxWidth(),
    )
}

@Preview(showBackground = true)
@Composable
fun NameFieldPreview() {
    NameField("Julie", onValueChange = {})
}