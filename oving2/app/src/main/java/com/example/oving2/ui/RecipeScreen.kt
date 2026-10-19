package com.example.oving2.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.oving2.R

@Composable
fun RecipeScreen (
    recipes: List<Recipe>,
    onRecipeClick: (Recipe) -> Unit,
    onInfoClick: () -> Unit,
    modifier: Modifier = Modifier,
    ) {
    Column(modifier = modifier.fillMaxSize()) {
        RecipeHeader()
        ClickableRecipeList(
            recipes = recipes,
            onRecipeClick = onRecipeClick,
            modifier = Modifier.weight(1f),
        )
        Button(
            onInfoClick,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        ) {
            Text(
                text = stringResource(R.string.show_info),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
