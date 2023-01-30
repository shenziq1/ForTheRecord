package com.github.shenziq1.fortherecord.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Blue200,
    primaryVariant = Blue500,
    secondary = Teal200,
    onSurface = Color.White
)

private val LightColorPalette = lightColors(
    primary = Blue200,
    primaryVariant = Blue300,
    secondary = Red100,
    background = Blue50,
    surface = Blue100

)

@Composable
fun ForTheRecordTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}