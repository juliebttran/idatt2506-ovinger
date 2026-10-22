package com.example.oving7

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MovieScreenWithViewModel(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = viewModel()
) {
    val result by viewModel.result.collectAsStateWithLifecycle()

    MovieScreen(
        result = result,
        modifier = modifier
    )
}