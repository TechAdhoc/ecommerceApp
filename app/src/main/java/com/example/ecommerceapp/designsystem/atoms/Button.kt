package com.example.ecommerceapp.designsystem.atoms

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.design.Theme
import com.example.ecommerceapp.designsystem.theme.Dimensions

/**
 * Primary button with filled background 
 */
@Composable
fun EcommercePrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = Dimensions.spacing_16,
        vertical = Dimensions.spacing_12
    ),
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(Dimensions.button_height),
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Theme.primary,
            contentColor = Theme.onPrimary,
            disabledContainerColor = Theme.primary.copy(alpha = 0.38f),
            disabledContentColor = Theme.onPrimary.copy(alpha = 0.38f)
        ),
        contentPadding = contentPadding,
        content = content
    )
}

/**
 * Secondary button with outlined style 
 */
@Composable
fun EcommerceSecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    borderColor: Color = Theme.primary,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = Dimensions.spacing_16,
        vertical = Dimensions.spacing_12
    ),
    content: @Composable RowScope.() -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(Dimensions.button_height),
        enabled = enabled,
        shape = shape,
        border = BorderStroke(1.dp, if (enabled) borderColor else borderColor.copy(alpha = 0.38f)),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            contentColor = Theme.primary,
            disabledContentColor = Theme.primary.copy(alpha = 0.38f)
        ),
        contentPadding = contentPadding,
        content = content
    )
}

/**
 * Text button with no background
 */
@Composable
fun EcommerceTextButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = Theme.bodyMedium,
    textColor: Color = Theme.primary,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = Dimensions.spacing_8,
        vertical = Dimensions.spacing_4
    )
) {
    TextButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        contentPadding = contentPadding,
        colors = ButtonDefaults.textButtonColors(
            contentColor = textColor,
            disabledContentColor = textColor.copy(alpha = 0.38f)
        )
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}
