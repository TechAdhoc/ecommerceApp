package com.example.ecommerceapp.designsystem.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.example.ecommerceapp.design.Theme
import com.example.ecommerceapp.designsystem.theme.Dimensions

/**
 * Standard icon component with consistent styling
 */
@Composable
fun EcommerceIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = Theme.onSurface,
    size: Dp = Dimensions.icon_size_medium
) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = modifier.size(size),
        tint = tint
    )
}

/**
 * Standard clickable icon with consistent styling
 */
@Composable
fun EcommerceIconButton(
    imageVector: ImageVector,
    onClick: () -> Unit,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = Theme.onSurface,
    enabled: Boolean = true
) {
    IconButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = if (enabled) tint else tint.copy(alpha = 0.38f)
        )
    }
}

/**
 * Icon with circular background
 */
@Composable
fun CircleBackgroundIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    iconTint: Color = Theme.onPrimary,
    backgroundColor: Color = Theme.primary,
    iconSize: Dp = Dimensions.icon_size_small,
    backgroundSize: Dp = Dimensions.icon_size_medium
) {
    Box(
        modifier = modifier
            .size(backgroundSize)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = iconTint,
            modifier = Modifier.size(iconSize)
        )
    }
}
