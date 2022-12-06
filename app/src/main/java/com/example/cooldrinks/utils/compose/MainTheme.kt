package com.example.cooldrinks.utils.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.cooldrinks.R
import com.example.cooldrinks.utils.compose.theme.*


val LightColor = lightColorScheme(
    primary = lightPrimaryColor,
    onPrimary = lightOnPrimaryColor,
    secondary = lightSecondaryColor,
    onSecondary = lightOnSecondaryColor,
    background = lightBackgroundColor,
    onBackground = lightOnBackgroundColor,
    surface = lightSurfaceColor,
    onSurface = lightOnSurfaceColor,
)

val DarkColor = darkColorScheme(
    primary = darkPrimaryColor,
    onPrimary = darkOnPrimaryColor,
    secondary = darkSecondaryColor,
    onSecondary = darkOnSecondaryColor,
    background = darkBackgroundColor,
    onBackground = darkOnBackgroundColor,
    surface = darkSurfaceColor,
    onSurface = darkOnSurfaceColor,
)

val ColorScheme.iconColor: Color
    @Composable
    get() = if (isSystemInDarkTheme().not()) Color(color = 0xFF727B8B) else Color(color = 0xFFAEB5BD)

val ColorScheme.textSecondary: Color
    @Composable
    get() = if (isSystemInDarkTheme().not()) Color(color = 0xFF979B9D) else Color(color = 0xFF858E9D)

val ColorScheme.textPrimary: Color
    @Composable
    get() = if (isSystemInDarkTheme().not()) Color(color = 0xFF222222) else Color(color = 0xFFFFFFFF)

@Composable
private fun getTypography() = MaterialTheme.typography.copy(
    headlineLarge = TextStyle.Default.copy(fontFamily = fonts, fontSize = fontDimensionResource(R.dimen.textSize_xxLarge)), //24sp
    headlineMedium = TextStyle.Default.copy(fontFamily = fonts, fontSize = fontDimensionResource(R.dimen.textSize_xLarge)), //22sp
    headlineSmall = TextStyle.Default.copy(fontFamily = fonts, fontSize = fontDimensionResource(R.dimen.textSize_large)), // 20sp
    titleMedium = TextStyle.Default.copy(fontFamily = fonts, fontSize = fontDimensionResource(R.dimen.textSize_xMedium)), //18sp
    bodyLarge = TextStyle.Default.copy(fontFamily = fonts, fontSize = fontDimensionResource(R.dimen.textSize_medium)), // 16sp
    bodyMedium = TextStyle.Default.copy(fontFamily = fonts, fontSize = fontDimensionResource(R.dimen.textSize_small)), // 14sp
    bodySmall = TextStyle.Default.copy(fontFamily = fonts, fontSize = fontDimensionResource(R.dimen.textSize_xSmall)), // 12sp
    labelMedium = TextStyle.Default.copy(fontFamily = fonts, fontSize = fontDimensionResource(R.dimen.textSize_xxSmall)), // 10sp
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColor else LightColor,
        typography = getTypography(),
        shapes = Shapes(),
        content = content
    )
}

