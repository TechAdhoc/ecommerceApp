package com.example.ecommerceapp.core.designsystem.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.core.designsystem.theme.Theme

/**
 * Divider atom used to create horizontal separators
 */
@Composable
fun EcommerceDivider(
    modifier: Modifier = Modifier,
    color: Color = Theme.onSurface.copy(alpha = 0.12f),
    thickness: Dp = 1.dp,
) {
    HorizontalDivider(
        modifier = modifier,
        color = color,
        thickness = thickness,
    )
}

/**
 * Surface atom provides a consistent style for background areas
 */
@Composable
fun EcommerceSurface(
    modifier: Modifier = Modifier,
    color: Color = Theme.surface,
    shape: Shape = ButtonDefaults.shape,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        color = color,
        shape = shape,
        content = content
    )
}

/**
 * Gradient background component 
 */
@Composable
fun GradientBackground(
    colors: List<Color>,
    modifier: Modifier = Modifier,
    shape: Shape = ButtonDefaults.shape
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(
                Brush.verticalGradient(colors)
            )
    )
}
