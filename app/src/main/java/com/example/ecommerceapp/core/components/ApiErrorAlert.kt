package com.example.ecommerceapp.core.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ecommerceapp.design.Theme

/**
 * Alert dialog component for displaying API errors
 */
@Composable
fun ApiErrorAlert(
    message: String,
    onRetry: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Error", style = Theme.titleLarge) },
        text = { Text(message, style = Theme.bodyMedium) },
        confirmButton = {
            Button(onClick = onRetry) {
                Text("Retry")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Dismiss")
            }
        },
        modifier = modifier
    )
}
