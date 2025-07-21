package com.example.ecommerceapp.designsystem.templates

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.design.Theme
import com.example.ecommerceapp.designsystem.atoms.EcommerceSurface
import com.example.ecommerceapp.designsystem.theme.Dimensions
import com.example.ecommerceapp.designsystem.theme.Primary
import com.example.ecommerceapp.designsystem.theme.PrimaryDark

/**
 * Centered form template for authentication screens
 */
@Composable
fun CenteredFormTemplate(
    modifier: Modifier = Modifier,
    topContent: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Primary,
                        PrimaryDark
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimensions.spacing_24)
                .verticalScroll(rememberScrollState())
        ) {
            // Top content (like logo, title, etc.)
            topContent()
            
            // Main content
            content()
        }
    }
}

/**
 * Bottom sheet template for forms that appear from the bottom
 */
@Composable
fun BottomSheetTemplate(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Theme.colorScheme.surface,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.spacing_24)
        ) {
            // Drag handle indicator
            Box(
                modifier = Modifier
                    .height(4.dp)
                    .width(40.dp)
                    .background(
                        color = Theme.colorScheme.onSurface.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(2.dp)
                    )
                    .align(Alignment.CenterHorizontally)
            )
            
            Spacer(modifier = Modifier.height(Dimensions.spacing_24))
            
            // Sheet content
            content()
        }
    }
}
