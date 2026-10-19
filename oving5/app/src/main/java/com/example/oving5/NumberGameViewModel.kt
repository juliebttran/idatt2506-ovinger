package com.example.oving5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

private const val GAME_URL = "https://bigdata.idi.ntnu.no/mobil/tallspill.jsp"

class NumberGameViewModel : ViewModel() {
    private val  network = HttpWrapper(GAME_URL)

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _number = MutableStateFlow("")
    val number: StateFlow<String> = _number.asStateFlow()

    private val _response = MutableStateFlow("")
    val response: StateFlow<String> = _response.asStateFlow()
    private val _gameStarted = MutableStateFlow(false)
    val gameStarted: StateFlow<Boolean> = _gameStarted.asStateFlow()

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onCardNumberChange(newCardNumber: String) {
        _cardNumber.value = newCardNumber
    }

    fun onNumberChange(newNumber: String) {
        _number.value = newNumber
    }
    fun startGame() {
        val parameters = mapOf(
            "navn" to _name.value,
            "kortnummer" to _cardNumber.value
        )
        send{
            network.get(parameters)
        }
        _gameStarted.value = true
    }

    fun sendGuess() {
        val parameters = mapOf(
            "tall" to _number.value
        )
        send {
            network.get(parameters)
        }
        _number.value = ""
    }

    fun newGame() {
        _name.value = ""
        _cardNumber.value = ""
        _number.value = ""
        _response.value = ""
        _gameStarted.value = false
    }

    private fun send (request: suspend () -> String) {
        viewModelScope.launch {
            try {
                val res = request().trim()
                _response.value = if (_response.value.isEmpty()) res else "${_response.value}\n$res"
            }
            catch (e: IOException) {
                _response.value = "${_response.value}\n${e.toString()}"            }
        }
    }

}