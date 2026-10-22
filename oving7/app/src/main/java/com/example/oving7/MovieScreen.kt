package com.example.oving7

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.oving7.ui.theme.Oving7Theme

fun colorFor(name: String): Color = when (name) {
    "bla" -> Color(0xFFB8D3FF)
    "gronn" -> Color(0xFFCCFCCF)
    "rod" -> Color(0xFFFCCFCC)
    else -> Color.Transparent
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    result: List<String>,
    color: String,
    onFindAllMovies: () -> Unit,
    onFindMovieByDirector: () -> Unit,
    onFindActorsInMovie: () -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name)) },
                actions = {
                    IconButton(onClick = onOpenSettings) {
                        Icon(
                            painter = painterResource(R.drawable.settings),
                            contentDescription = stringResource(R.string.settings)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(colorFor(color))
                .padding(16.dp)
        ) {
            MovieActions(onFindAllMovies = onFindAllMovies, onFindMovieByDirector = onFindMovieByDirector, onFindActorsInMovie = onFindActorsInMovie)
            LazyColumn { items(result) { row -> Text(text = row) } }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieScreenPreview(){
    Oving7Theme {
        MovieScreen(
            result = listOf("Ian McShane", "Lance Reddick"),
            color = "gronn",
            onFindAllMovies = {},
            onFindMovieByDirector = {},
            onFindActorsInMovie = {},
            onOpenSettings = {}
        )
    }
}