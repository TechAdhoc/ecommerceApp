package com.example.ecommerceapp.core.designsystem.molecules

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.core.designsystem.atoms.EcommerceSurface
import com.example.ecommerceapp.core.designsystem.theme.Dimensions
import com.example.ecommerceapp.core.designsystem.theme.Theme

/**
 * InfoBanner molecule component for displaying informational messages
 * Can be used for success, error, warning and info messages
 */
@Composable
fun InfoBanner(
    message: String,
    modifier: Modifier = Modifier,
    type: InfoBannerType = InfoBannerType.INFO,
    onDismiss: (() -> Unit)? = null
) {
    // Define default colors for different banner types
    val successColor = Color(0xFF4CAF50)  // Green
    val warningColor = Color(0xFFFFC107)  // Amber
    val infoColor = Color(0xFF2196F3)     // Blue

    val (backgroundColor, contentColor, icon) = when (type) {
        InfoBannerType.SUCCESS -> Triple(
            successColor.copy(alpha = 0.1f),
            successColor,
            Icons.Default.Info
        )
        InfoBannerType.ERROR -> Triple(
            Theme.error.copy(alpha = 0.1f),
            Theme.error,
            Icons.Default.Warning
        )
        InfoBannerType.WARNING -> Triple(
            warningColor.copy(alpha = 0.1f),
            warningColor,
            Icons.Default.Warning
        )
        InfoBannerType.INFO -> Triple(
            infoColor.copy(alpha = 0.1f),
            infoColor,
            Icons.Default.Info
        )
        InfoBannerType.LOADING -> Triple(
            Theme.primary.copy(alpha = 0.1f),
            Theme.primary,
            null
        )
    }

    EcommerceSurface(
        modifier = modifier.fillMaxWidth(),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier.padding(Dimensions.spacing_16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (type == InfoBannerType.LOADING) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = contentColor,
                    strokeWidth = 2.dp
                )
            } else {
                icon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = type.name,
                        tint = contentColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(Dimensions.spacing_16))

            Text(
                text = message,
                style = Theme.bodyMedium,
                color = contentColor,
                modifier = Modifier.weight(1f)
            )

            onDismiss?.let { dismiss ->
                // Optional dismiss button implementation could go here
            }
        }
    }
}

/**
 * Type definitions for different kinds of info banners
 */
enum class InfoBannerType {
    SUCCESS,
    ERROR,
    WARNING,
    INFO,
    LOADING
}
