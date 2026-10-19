package com.example.oving2.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Brown,
    onPrimary = Grey10,
    surface = PureCreme,
    onSurface = Grey10,
    onSurfaceVariant = Grey99,
    onBackground = LightBrown,
)

@Composable
fun Oving2Theme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}