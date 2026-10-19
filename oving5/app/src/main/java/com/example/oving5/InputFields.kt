package com.example.oving5

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun NameField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text(text = stringResource(R.string.name))},
        modifier = modifier,
    )
}

@Composable
fun CardNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text(text = stringResource(R.string.card_number))},
        modifier = modifier,
    )
}

@Composable
fun NumberField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text(text = stringResource(R.string.number))},
        modifier = modifier,
    )
}