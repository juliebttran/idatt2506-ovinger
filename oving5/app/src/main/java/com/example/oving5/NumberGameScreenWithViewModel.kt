package com.example.oving5

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun NumberGameScreenWithViewModel (
    modifier: Modifier = Modifier,
    numberGameViewModel: NumberGameViewModel = viewModel(),
){
    val name by  numberGameViewModel.name.collectAsStateWithLifecycle()
    val cardNumber by numberGameViewModel.cardNumber.collectAsStateWithLifecycle()
    val number by numberGameViewModel.number.collectAsStateWithLifecycle()
    val response by numberGameViewModel.response.collectAsStateWithLifecycle()
    val gameStarted by numberGameViewModel.gameStarted.collectAsStateWithLifecycle()

    NumberGameScreen(
        name = name,
        onNameChange = numberGameViewModel::onNameChange,
        cardNumber = cardNumber,
        onCardNumberChange = numberGameViewModel::onCardNumberChange,
        number = number,
        onNumberChange = numberGameViewModel::onNumberChange,
        response = response,
        gameStarted = gameStarted,
        onStartGame = numberGameViewModel::startGame,
        onSendGuess = numberGameViewModel::sendGuess,
        onNewGame = numberGameViewModel::newGame,
        modifier = modifier
    )
}