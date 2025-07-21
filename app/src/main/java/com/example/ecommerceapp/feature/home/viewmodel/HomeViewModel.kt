package com.example.ecommerceapp.feature.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.core.base.BaseViewModel
import com.example.ecommerceapp.core.network.ApiResult
import com.example.ecommerceapp.core.network.NetworkConnectivityManager
import com.example.ecommerceapp.data.model.Product
import com.example.ecommerceapp.domain.repository.AuthRepository
import com.example.ecommerceapp.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val authRepository: AuthRepository,
    networkConnectivityManager: NetworkConnectivityManager
) : BaseViewModel(networkConnectivityManager) {

    private val _featuredProducts = MutableStateFlow<ApiResult<List<Product>>>(ApiResult.Loading)
    val featuredProducts: StateFlow<ApiResult<List<Product>>> = _featuredProducts.asStateFlow()

    private val _newArrivals = MutableStateFlow<ApiResult<List<Product>>>(ApiResult.Loading)
    val newArrivals: StateFlow<ApiResult<List<Product>>> = _newArrivals.asStateFlow()

    private val _popularProducts = MutableStateFlow<ApiResult<List<Product>>>(ApiResult.Loading)
    val popularProducts: StateFlow<ApiResult<List<Product>>> = _popularProducts.asStateFlow()

    init {
        loadHomeData()
    }

    fun loadHomeData() {
        loadFeaturedProducts()
        loadNewArrivals()
        loadPopularProducts()
    }

    // Clear error states to dismiss error dialogs
    fun clearFeaturedProductsError() {
        // Replace the error with Loading state to trigger a refresh
        if (_featuredProducts.value is ApiResult.Error) {
            _featuredProducts.value = ApiResult.Loading
            loadFeaturedProducts()
        }
    }

    fun clearNewArrivalsError() {
        if (_newArrivals.value is ApiResult.Error) {
            _newArrivals.value = ApiResult.Loading
            loadNewArrivals()
        }
    }

    fun clearPopularProductsError() {
        if (_popularProducts.value is ApiResult.Error) {
            _popularProducts.value = ApiResult.Loading
            loadPopularProducts()
        }
    }

    private fun loadFeaturedProducts() {
        viewModelScope.launch {
            getProductsUseCase(limit = 5)
                .catch { 
                    handleApiError(it, _featuredProducts)
                }
                .collect { result ->
                    if (result is ApiResult.Error && result.code == 401) {
                        handleSessionExpiry()
                    } else {
                        _featuredProducts.value = result
                    }
                }
        }
    }

    private fun loadNewArrivals() {
        viewModelScope.launch {
            getProductsUseCase(limit = 5)
                .catch { 
                    handleApiError(it, _newArrivals)
                }
                .collect { result ->
                    if (result is ApiResult.Error && result.code == 401) {
                        handleSessionExpiry()
                    } else {
                        _newArrivals.value = result
                    }
                }
        }
    }

    private fun loadPopularProducts() {
        viewModelScope.launch {
            getProductsUseCase(limit = 10)
                .catch { 
                    handleApiError(it, _popularProducts)
                }
                .collect { result ->
                    if (result is ApiResult.Error && result.code == 401) {
                        handleSessionExpiry()
                    } else {
                        _popularProducts.value = result
                    }
                }
        }
    }

    private fun <T> handleApiError(throwable: Throwable, stateFlow: MutableStateFlow<ApiResult<T>>) {
        val errorType = if (!isNetworkAvailable()) {
            ApiResult.ErrorType.NETWORK
        } else {
            ApiResult.ErrorType.UNKNOWN
        }
        
        stateFlow.value = ApiResult.Error(
            message = throwable.message ?: "Unknown error occurred",
            errorType = errorType
        )
    }

    private fun handleSessionExpiry() {
        viewModelScope.launch {
            authRepository.handleSessionExpiry().collect { /* Session handled by repository */ }
        }
    }
}
