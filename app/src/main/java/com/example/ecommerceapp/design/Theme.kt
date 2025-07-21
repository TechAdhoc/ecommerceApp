package com.example.ecommerceapp.design

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

// Light Theme Colors
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2196F3),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFBBDEFB),
    onPrimaryContainer = Color(0xFF0D47A1),
    secondary = Color(0xFFFF9800),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFFFFE0B2),
    onSecondaryContainer = Color(0xFFE65100),
    tertiary = Color(0xFF4CAF50),
    onTertiary = Color.White,
    error = Color(0xFFF44336),
    onError = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black
)

// Dark Theme Colors
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF90CAF9),
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF1976D2),
    onPrimaryContainer = Color(0xFFBBDEFB),
    secondary = Color(0xFFFFB74D),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFFE65100),
    onSecondaryContainer = Color(0xFFFFE0B2),
    tertiary = Color(0xFF81C784),
    onTertiary = Color.Black,
    error = Color(0xFFE57373),
    onError = Color.Black,
    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF121212),
    onSurface = Color.White
)

// Local provider for our theme
private val LocalAppColorScheme = staticCompositionLocalOf { LightColorScheme }

/**
 * Design system Theme class that provides consistent theming throughout the app
 */
object Theme {
    /**
     * Main Theme composable that applies our custom theme to the entire app
     */
    @RequiresApi(Build.VERSION_CODES.R)
    @Composable
    fun AppTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        dynamicColor: Boolean = true,
        content: @Composable () -> Unit
    ) {
        val colorScheme = when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }
            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }

        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                val window = (view.context as Activity).window

                // Set system bars (status and navigation) color and appearance
                WindowCompat.setDecorFitsSystemWindows(window, false)
                val controller = WindowInsetsControllerCompat(window, view)
                controller.isAppearanceLightStatusBars = !darkTheme
                controller.isAppearanceLightNavigationBars = !darkTheme

                window.setDecorFitsSystemWindows(false)
                WindowCompat.getInsetsController(window, view).apply {
                    isAppearanceLightStatusBars = !darkTheme

                    window.statusBarColor = Color.Transparent.toArgb()
                }
            }
        }

        CompositionLocalProvider(LocalAppColorScheme provides colorScheme) {
            MaterialTheme(
                colorScheme = colorScheme,
                typography = MaterialTheme.typography,
                content = content
            )
        }
    }

    /**
     * Typography styles for the application
     */
    val typography: Typography
        @Composable
        get() = MaterialTheme.typography

    /**
     * Color scheme for the application
     */
    val colorScheme: ColorScheme
        @Composable
        get() = LocalAppColorScheme.current

    // Typography convenience accessors
    val headlineLarge: TextStyle
        @Composable
        get() = typography.headlineLarge

    val headlineMedium: TextStyle
        @Composable
        get() = typography.headlineMedium

    val headlineSmall: TextStyle
        @Composable
        get() = typography.headlineSmall

    val titleLarge: TextStyle
        @Composable
        get() = typography.titleLarge

    val titleMedium: TextStyle
        @Composable
        get() = typography.titleMedium

    val titleSmall: TextStyle
        @Composable
        get() = typography.titleSmall

    val bodyLarge: TextStyle
        @Composable
        get() = typography.bodyLarge

    val bodyMedium: TextStyle
        @Composable
        get() = typography.bodyMedium

    val bodySmall: TextStyle
        @Composable
        get() = typography.bodySmall

    // Color convenience accessors
    val primary: Color
        @Composable
        get() = colorScheme.primary

    val onPrimary: Color
        @Composable
        get() = colorScheme.onPrimary

    val primaryContainer: Color
        @Composable
        get() = colorScheme.primaryContainer

    val onPrimaryContainer: Color
        @Composable
        get() = colorScheme.onPrimaryContainer

    val secondary: Color
        @Composable
        get() = colorScheme.secondary

    val onSecondary: Color
        @Composable
        get() = colorScheme.onSecondary

    val secondaryContainer: Color
        @Composable
        get() = colorScheme.secondaryContainer

    val onSecondaryContainer: Color
        @Composable
        get() = colorScheme.onSecondaryContainer

    val surface: Color
        @Composable
        get() = colorScheme.surface

    val onSurface: Color
        @Composable
        get() = colorScheme.onSurface

    val background: Color
        @Composable
        get() = colorScheme.background

    val onBackground: Color
        @Composable
        get() = colorScheme.onBackground

    val error: Color
        @Composable
        get() = colorScheme.error

    val onError: Color
        @Composable
        get() = colorScheme.onError
}
