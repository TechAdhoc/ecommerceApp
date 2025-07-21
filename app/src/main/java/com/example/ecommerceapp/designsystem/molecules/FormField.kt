package com.example.ecommerceapp.designsystem.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import com.example.ecommerceapp.design.Theme
import com.example.ecommerceapp.designsystem.atoms.EcommerceTextField
import com.example.ecommerceapp.designsystem.theme.Dimensions

/**
 * Form field molecule that combines a label with a text field
 */
@Composable
fun FormField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
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
    enabled: Boolean = true,
    helperText: String? = null
) {
    Column(modifier = modifier.fillMaxWidth()) {
        EcommerceTextField(
            value = value,
            onValueChange = onValueChange,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            errorMessage = errorMessage,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            enabled = enabled
        )
        
        if (!isError && helperText != null) {
            Spacer(modifier = Modifier.height(Dimensions.spacing_4))
            Text(
                text = helperText,
                style = Theme.typography.bodyMedium,
                color = Theme.colorScheme.onSurface.copy(alpha = 0.6f),
                modifier = Modifier.padding(start = Dimensions.spacing_4)
            )
        }
    }
}
