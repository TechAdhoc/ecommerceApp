package com.example.ecommerceapp.core.designsystem.atoms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import com.example.ecommerceapp.core.designsystem.theme.Dimensions
import com.example.ecommerceapp.core.designsystem.theme.Theme

/**
 * Standard text input field with consistent styling
 */
@Composable
fun EcommerceTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    shape: Shape = ButtonDefaults.shape,
    enabled: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = label?.let { { Text(text = it) } },
        placeholder = placeholder?.let { { Text(text = it) } },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        shape = shape,
        enabled = enabled,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Theme.onSurface,
            unfocusedTextColor = Theme.onSurface,
            disabledTextColor = Theme.onSurface.copy(alpha = 0.38f),
            errorTextColor = Theme.onSurface,
            cursorColor = Theme.primary,
            errorCursorColor = Theme.error,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            focusedIndicatorColor = Theme.primary,
            unfocusedIndicatorColor = Theme.onSurface.copy(alpha = 0.2f),
            disabledIndicatorColor = Theme.onSurface.copy(alpha = 0.12f),
            errorIndicatorColor = Theme.error,
            focusedLabelColor = Theme.primary,
            unfocusedLabelColor = Theme.onSurface.copy(alpha = 0.6f),
            disabledLabelColor = Theme.onSurface.copy(alpha = 0.38f),
            errorLabelColor = Theme.error,
            focusedPlaceholderColor = Theme.onSurface.copy(alpha = 0.4f),
            unfocusedPlaceholderColor = Theme.onSurface.copy(alpha = 0.4f),
            disabledPlaceholderColor = Theme.onSurface.copy(alpha = 0.38f),
            errorPlaceholderColor = Theme.onSurface.copy(alpha = 0.4f)
        )
    )
    
    if (isError && errorMessage != null) {
        Text(
            text = errorMessage,
            color = Theme.error,
            style = Theme.bodySmall,
            modifier = Modifier.padding(start = Dimensions.spacing_4, top = Dimensions.spacing_2)
        )
    }
}
