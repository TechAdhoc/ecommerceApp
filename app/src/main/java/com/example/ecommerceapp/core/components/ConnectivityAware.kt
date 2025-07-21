package com.example.ecommerceapp.core.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.ecommerceapp.core.network.ApiResult
import com.example.ecommerceapp.core.network.NetworkConnectivityManager
import com.example.ecommerceapp.core.network.observeConnectivityAsState

/**
 * A composable that handles network connectivity and API errors
 * Shows full-screen errors for internet connectivity issues and
 * alert popups for API failures
 */
@Composable
fun <T> ConnectivityAware(
    networkConnectivityManager: NetworkConnectivityManager,
    apiResult: ApiResult<T>?,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (data: T) -> Unit
) {
    val isConnected by networkConnectivityManager.observeConnectivityAsState()
    var showApiErrorDialog by remember { mutableStateOf(false) }
    var apiErrorMessage by remember { mutableStateOf("") }

    when {
        // No internet connection - show full screen error
        !isConnected -> NetworkErrorScreen(
            onRetry = onRetry,
            modifier = modifier
        )
        
        // API result handling
        apiResult is ApiResult.Success -> content(apiResult.data)
        
        apiResult is ApiResult.Error -> {
            // For network errors, show full-screen error
            if (apiResult.errorType == ApiResult.ErrorType.NETWORK) {
                NetworkErrorScreen(
                    onRetry = onRetry,
                    modifier = modifier
                )
            } else {
                // For API errors, show a fallback UI or placeholder content
                // since ApiResult.Error doesn't have valid data
                content(null as T)

                // Set error message
                apiErrorMessage = when (apiResult.errorType) {
                    ApiResult.ErrorType.AUTH_ERROR -> "Authentication error. Please login again."
                    else -> apiResult.message.ifEmpty { "An unexpected error occurred." }
                }
                showApiErrorDialog = true

                // Show alert dialog for API errors
                if (showApiErrorDialog) {
                    ApiErrorAlert(
                        message = apiErrorMessage,
                        onRetry = {
                            showApiErrorDialog = false
                            onRetry()
                        },
                        onDismiss = {
                            showApiErrorDialog = false
                        }
                    )
                }
            }
        }
        
        apiResult is ApiResult.Loading -> {
            // Content is loading, show loading indicator
            Loader(isLoading = true, fullScreen = true, modifier = modifier)
        }

        // If apiResult is null, just show content with empty data
        else -> content(null as T)
    }
}

/**
 * Simplified version that doesn't require network connectivity manager parameter
 * Shows full-screen errors for internet connectivity issues and
 * alert popups for API failures
 */
@Composable
fun <T> ApiResultHandler(
    apiResult: ApiResult<T>,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    loadingContent: @Composable () -> Unit = { Loader(isLoading = true, modifier = Modifier) },
    content: @Composable (data: T) -> Unit
) {
    var showApiErrorDialog by remember { mutableStateOf(false) }
    var apiErrorMessage by remember { mutableStateOf("") }

    when (apiResult) {
        is ApiResult.Success -> content(apiResult.data)
        
        is ApiResult.Error -> {
            // For network errors, show full-screen error
            if (apiResult.errorType == ApiResult.ErrorType.NETWORK) {
                NetworkErrorScreen(
                    onRetry = onRetry,
                    modifier = modifier
                )
            } else {
                // For API errors, show a fallback UI or placeholder content
                content(null as T)

                apiErrorMessage = when (apiResult.errorType) {
                    ApiResult.ErrorType.AUTH_ERROR -> "Authentication error. Please login again."
                    else -> apiResult.message.ifEmpty { "An unexpected error occurred." }
                }
                showApiErrorDialog = true

                if (showApiErrorDialog) {
                    ApiErrorAlert(
                        message = apiErrorMessage,
                        onRetry = {
                            showApiErrorDialog = false
                            onRetry()
                        },
                        onDismiss = {
                            showApiErrorDialog = false
                        }
                    )
                }
            }
        }
        
        is ApiResult.Loading -> loadingContent()
        ApiResult.Initial -> Unit
    }
}
