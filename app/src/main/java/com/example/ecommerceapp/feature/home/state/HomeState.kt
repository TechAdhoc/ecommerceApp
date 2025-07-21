package com.example.ecommerceapp.feature.home.state

import com.example.ecommerceapp.data.model.Product

// HomeScreen state
data class HomeScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val featuredProducts: List<Product> = emptyList(),
    val newArrivals: List<Product> = emptyList(),
    val popularProducts: List<Product> = emptyList()
)

// Product detail state
data class ProductDetailState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val product: Product? = null
)
