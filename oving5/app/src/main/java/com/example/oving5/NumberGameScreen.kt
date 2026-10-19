package com.example.oving5

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.oving5.ui.theme.Oving5Theme

@Composable
fun NumberGameScreen(
    name: String,
    onNameChange: (String) -> Unit,
    cardNumber: String,
    onCardNumberChange: (String) -> Unit,
    number: String,
    onNumberChange: (String) -> Unit,
    response: String,
    gameStarted: Boolean,
    onStartGame: () -> Unit,
    onSendGuess: () -> Unit,
    onNewGame: () -> Unit,
    modifier: Modifier = Modifier,
){
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            NumberGameTopBar(
                onNewGame = onNewGame,
            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!gameStarted) {
                Text(
                    text = stringResource(R.string.get_started),
                    style = MaterialTheme.typography.titleMedium
                )
                NameField(
                    value = name,
                    onValueChange = onNameChange,
                    modifier = Modifier.fillMaxWidth()
                )
                CardNumberField(
                    value = cardNumber,
                    onValueChange = onCardNumberChange,
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = onStartGame,
                    enabled = name.isNotBlank() && cardNumber.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.start_game))
                }
            } else {
                NumberField(
                    value = number,
                    onValueChange = onNumberChange,
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = onSendGuess,
                    enabled = number.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.send))
                }
                Text(
                    text = stringResource(R.string.feedback),
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if(response.isEmpty()) {
                    item{
                        Text(
                            text = "",
                        )
                    }
                }
                else {
                    items(response.lines()) {line ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {
                            Text(
                                text = line,
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NumberGameScreenPreview() {
    Oving5Theme {
        NumberGameScreen(
            name = "Ola Nordmann",
            onNameChange = {},
            cardNumber = "123",
            onCardNumberChange = {},
            number = "",
            onNumberChange = {},
            response = "",
            gameStarted = false,
            onStartGame = {},
            onSendGuess = {},
            onNewGame = {}
        )
    }
}