package com.example.oving7

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun MovieActions(
    onFindAllMovies: () -> Unit,
    onFindMovieByDirector: () -> Unit,
    onFindActorsInMovie: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Button(
            onClick = onFindAllMovies,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.all_movies))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onFindMovieByDirector,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.movies_by_christopher_nolan))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onFindActorsInMovie,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.actors_in_the_matrix))
        }
    }
}
