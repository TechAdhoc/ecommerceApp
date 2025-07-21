package com.example.ecommerceapp.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Color palettes for light and dark themes
 */
private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    primaryContainer = PrimaryDark,
    secondary = Secondary,
    secondaryContainer = SecondaryVariant,
    background = BackgroundDark,
    surface = SurfaceDark,
    error = Error,
    onPrimary = White,
    onSecondary = Black,
    onBackground = TextPrimaryDark,
    onSurface = TextPrimaryDark,
    onError = White
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    primaryContainer = PrimaryVariant,
    secondary = Secondary,
    secondaryContainer = SecondaryVariant,
    background = BackgroundLight,
    surface = SurfaceLight,
    error = Error,
    onPrimary = White,
    onSecondary = Black,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onError = White
)

/**
 * Additional design system values not covered by Material Theme
 */
data class ExtendedColors(
    val success: Color,
    val warning: Color,
    val info: Color,
    val textSecondary: Color
)

private val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        success = Success,
        warning = Warning,
        info = Info,
        textSecondary = TextSecondary
    )
}

/**
 * Spacing values for consistent padding/margins throughout the app
 */
data class Spacing(
    val xs: Int = 4,
    val small: Int = 8,
    val medium: Int = 16,
    val large: Int = 24,
    val xl: Int = 32,
    val xxl: Int = 48
)

private val LocalSpacing = staticCompositionLocalOf { Spacing() }

/**
 * Access to custom design system values
 */
object EcommerceDesignSystem {
    val extendedColors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
    
    val spacing: Spacing
        @Composable
        get() = LocalSpacing.current
}

/**
 * Ecommerce app theme that wraps Material Theme with additional design system values
 */
@Composable
fun ECommerceAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }
    
    val extendedColors = if (darkTheme) {
        ExtendedColors(
            success = Success,
            warning = Warning,
            info = Info,
            textSecondary = TextSecondaryDark
        )
    } else {
        ExtendedColors(
            success = Success,
            warning = Warning,
            info = Info,
            textSecondary = TextSecondary
        )
    }

    CompositionLocalProvider(
        LocalExtendedColors provides extendedColors,
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = EcommerceTypography,
            shapes = EcommerceShapes,
            content = content
        )
    }
}
