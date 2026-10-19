package com.example.oving2.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecipeList(recipes: List<Recipe>, modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 8.dp),
        modifier = modifier,
    ) {
        items(recipes) {recipe -> RecipeListItem(recipe = recipe)
        }
    }
}