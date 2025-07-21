package com.example.ecommerceapp.core.components

/**
 * Represents an error state that can be used to display error dialogs consistently across the app.
 * This is a common model for error handling that can be reused in multiple screens.
 */
data class ErrorState(
    val message: String,
    val onRetry: () -> Unit,
    val onDismiss: () -> Unit
)
