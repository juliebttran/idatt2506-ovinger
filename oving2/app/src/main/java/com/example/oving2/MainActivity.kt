package com.example.oving2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.oving2.ui.RecipeScreen
import com.example.oving2.ui.sampleRecipes
import com.example.oving2.ui.theme.Oving2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Oving2Theme {
                val context = LocalContext.current
                val message = stringResource(R.string.info_message)
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    innerPadding ->
                    RecipeScreen(
                        recipes = sampleRecipes,
                        onRecipeClick = { recipe ->
                            Toast.makeText(context, recipe.name, Toast.LENGTH_SHORT).show()
                        },
                        {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 600)
@Composable
fun RecipeScreenPreview() {
    Oving2Theme {
        RecipeScreen(
            recipes = sampleRecipes,
            onRecipeClick = {},
            onInfoClick = {},
        )
    }
}