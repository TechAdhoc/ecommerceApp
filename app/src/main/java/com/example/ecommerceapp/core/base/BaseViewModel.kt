package com.example.ecommerceapp.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.core.network.ApiResult
import com.example.ecommerceapp.core.network.NetworkConnectivityManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Base ViewModel class that provides common functionality for all ViewModels
 * including network connectivity monitoring
 */
abstract class BaseViewModel(
    private val networkConnectivityManager: NetworkConnectivityManager
) : ViewModel() {

    private val _isConnected = MutableStateFlow(networkConnectivityManager.isCurrentlyConnected())
    val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()

    init {
        observeNetworkConnectivity()
    }

    private fun observeNetworkConnectivity() {
        viewModelScope.launch {
            networkConnectivityManager.observeConnectivity().collect { isConnected ->
                _isConnected.value = isConnected
                onNetworkConnectivityChanged(isConnected)
            }
        }
    }

    /**
     * Called when network connectivity changes
     * Override in subclasses to handle connectivity changes
     */
    open fun onNetworkConnectivityChanged(isConnected: Boolean) {
        // Subclasses can override to handle connectivity changes
    }

    /**
     * Helper function to determine if an error is due to network connectivity
     */
    protected fun isNetworkError(error: ApiResult.Error): Boolean {
        return error.errorType == ApiResult.ErrorType.NETWORK ||
               error.errorType == ApiResult.ErrorType.TIMEOUT
    }

    /**
     * Check if the network is available
     * @return true if network is available, false otherwise
     */
    fun isNetworkAvailable(): Boolean {
        return networkConnectivityManager.isCurrentlyConnected()
    }
}
