package com.example.oving2.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.oving2.R

@Composable
fun RecipeHeader (modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp),
    ) {
        Image (
            painter = painterResource(R.drawable.header),
            stringResource(R.string.header_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .background(MaterialTheme.colorScheme.primary)
                .padding(9.dp),
            ) {
            Text(
                text = stringResource(R.string.header_label),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .background(MaterialTheme.colorScheme.onBackground)
                .padding(9.dp),
        ) {
            Text(
                text = stringResource(R.string.screen_title),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}