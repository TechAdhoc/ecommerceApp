package com.example.ecommerceapp.designsystem.molecules

import android.content.res.Resources
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.design.Theme
import com.example.ecommerceapp.designsystem.atoms.EcommerceSurface
import com.example.ecommerceapp.designsystem.theme.Dimensions
import com.example.ecommerceapp.designsystem.theme.EcommerceDesignSystem

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
    val (backgroundColor, contentColor, icon) = when (type) {
        InfoBannerType.SUCCESS -> Triple(
            EcommerceDesignSystem.extendedColors.success.copy(alpha = 0.1f),
            EcommerceDesignSystem.extendedColors.success, 
            Icons.Default.Info
        )
        InfoBannerType.ERROR -> Triple(Theme.colorScheme.error.copy(alpha = 0.1f),
            Theme.colorScheme.error,
            Icons.Default.Warning
        )
        InfoBannerType.WARNING -> Triple(
            EcommerceDesignSystem.extendedColors.warning.copy(alpha = 0.1f),
            EcommerceDesignSystem.extendedColors.warning, 
            Icons.Default.Warning
        )
        InfoBannerType.INFO -> Triple(
            EcommerceDesignSystem.extendedColors.info.copy(alpha = 0.1f),
            EcommerceDesignSystem.extendedColors.info, 
            Icons.Default.Info
        )
    }
    
    EcommerceSurface(
        modifier = modifier.fillMaxWidth(),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.spacing_16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = type.name,
                tint = contentColor,
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.width(Dimensions.spacing_16))
            
            Text(
                text = message,
                color =  Theme.colorScheme.onSurface,
                style = Theme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            
            if (onDismiss != null) {
                Spacer(modifier = Modifier.width(Dimensions.spacing_8))
                // Here you can add a close button if needed
            }
        }
    }
}

/**
 * LoadingBanner molecule component for displaying loading states with optional message
 */
@Composable
fun LoadingBanner(
    message: String? = null,
    modifier: Modifier = Modifier
) {
    EcommerceSurface(
        modifier = modifier.fillMaxWidth(),
        color =  Theme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.spacing_16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(
                color =  Theme.colorScheme.primary,
                modifier = Modifier.size(24.dp),
                strokeWidth = 2.dp
            )
            
            message?.let {
                Spacer(modifier = Modifier.width(Dimensions.spacing_16))
                
                Text(
                    text = it,
                    color =  Theme.colorScheme.onSurface,
                    style = Theme.typography.bodyMedium
                )
            }
        }
    }
}

enum class InfoBannerType {
    SUCCESS, ERROR, WARNING, INFO
}
