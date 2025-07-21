package com.example.ecommerceapp.core.designsystem.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.ecommerceapp.core.designsystem.theme.Primary
import com.example.ecommerceapp.core.designsystem.theme.PrimaryDark
import android.app.Activity
import androidx.core.view.WindowInsetsCompat

/**
 * Apply status bar color based on the current theme
 */
@Composable
fun ApplySystemBarColors(
    statusBarColor: Color = if (isSystemInDarkTheme()) PrimaryDark else Primary,
    navigationBarColor: Color = Color.Transparent,
    darkIcons: Boolean = !isSystemInDarkTheme()
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            // Make the system bars go edge-to-edge
            WindowCompat.setDecorFitsSystemWindows(window, false)

            // Get the controller
            val controller = WindowCompat.getInsetsController(window, view)

            // Control system bar appearance
            controller.isAppearanceLightStatusBars = darkIcons
            controller.isAppearanceLightNavigationBars = darkIcons

            // For transparent system bars, you can hide them
            // For colored system bars, we need to set them to be visible
            // and apply a scrim behind them
            if (statusBarColor == Color.Transparent) {
                controller.hide(WindowInsetsCompat.Type.statusBars())
            } else {
                controller.show(WindowInsetsCompat.Type.statusBars())
                // The scrim effect is applied through the system automatically
            }

            if (navigationBarColor == Color.Transparent) {
                controller.hide(WindowInsetsCompat.Type.navigationBars())
            } else {
                controller.show(WindowInsetsCompat.Type.navigationBars())
            }
        }
    }
}
