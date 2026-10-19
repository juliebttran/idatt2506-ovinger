package com.example.oving2.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ClickableRecipeList (
    recipes: List<Recipe>,
    onRecipeClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        items(recipes) {recipe ->
            RecipeListItem(
                recipe = recipe,
                modifier = Modifier.clickable {onRecipeClick(recipe)},
            )
        }
    }
}