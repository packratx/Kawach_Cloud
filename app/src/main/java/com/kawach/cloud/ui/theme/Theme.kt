package com.kawach.cloud

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val KawachLightColors = lightColorScheme(
    primary = Color(0xFF4F8EF7),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFF7AA7FF),
    background = Color(0xFFF4F7FF),
    surface = Color(0xFFEAF1FF),
    onBackground = Color(0xFF0F172A),
    onSurface = Color(0xFF0F172A),
    tertiary = Color(0xFF8F9DF7),
    onTertiary = Color(0xFFFFFFFF),
    error = Color(0xFFE57373)
)

private val KawachDarkColors = darkColorScheme(
    primary = Color(0xFF7AA7FF),
    onPrimary = Color(0xFF081120),
    secondary = Color(0xFF9CB8FF),
    background = Color(0xFF070E1B),
    surface = Color(0xFF101B2E),
    onBackground = Color(0xFFEAF2FF),
    onSurface = Color(0xFFEAF2FF),
    tertiary = Color(0xFF98A6FF),
    onTertiary = Color(0xFF081120),
    error = Color(0xFFEF9A9A)
)

@Composable
fun KawachCloudTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme = when {
        dynamicColor && android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> KawachDarkColors
        else -> KawachLightColors
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as android.app.Activity).window
            window.statusBarColor = android.graphics.Color.TRANSPARENT
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
