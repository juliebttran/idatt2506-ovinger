package com.example.oving7

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MovieScreenWithViewModel(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = viewModel()
) {
    val navController = rememberNavController()
    val result by viewModel.result.collectAsStateWithLifecycle()
    val color by viewModel.color.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = Movies,
        modifier = modifier
    ) {
        composable<Movies> {
            MovieScreen(
                result = result,
                color = color,
                onFindAllMovies = viewModel::findAllMovies,
                onFindMovieByDirector = {viewModel.findMovieByDirector("Christopher Nolan")},
                onFindActorsInMovie = {viewModel.findActorsInMovie("The Matrix")},
                onOpenSettings = { navController.navigate(Settings) }
            )
        }
        composable<Settings> {
            SettingsScreen(color = color, onChoose = viewModel::chooseColor)
        }
    }
}