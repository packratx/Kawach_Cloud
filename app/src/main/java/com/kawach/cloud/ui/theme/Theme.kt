package com.kawach.cloud.ui.theme

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
    primary = Color(0xFF2D6DF6),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFF5B8DEF),
    background = Color(0xFFF5F8FF),
    surface = Color(0xFFEAF1FF),
    onBackground = Color(0xFF101828),
    onSurface = Color(0xFF101828),
    tertiary = Color(0xFF66D6C6),
    onTertiary = Color(0xFFFFFFFF),
    error = Color(0xFFE57373)
)

private val KawachDarkColors = darkColorScheme(
    primary = Color(0xFF8AB4FF),
    onPrimary = Color(0xFF07131F),
    secondary = Color(0xFFB1C7FF),
    background = Color(0xFF07131F),
    surface = Color(0xFF101C2D),
    onBackground = Color(0xFFEAF5FF),
    onSurface = Color(0xFFEAF5FF),
    tertiary = Color(0xFF7EE7D8),
    onTertiary = Color(0xFF07131F),
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
        typography = KawachTypography,
        content = content
    )
}
