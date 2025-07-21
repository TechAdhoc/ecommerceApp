package com.example.ecommerceapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Product model for ecommerce app aligned with Fakestore API
 */
@Serializable
data class Product(
    val id: Int,
    @SerialName("title") val name: String,
    val description: String,
    val price: Double,
    val category: String,
    @SerialName("image") val imageUrl: String,
    val rating: Rating
) {
    // Backward compatibility getters
    val rate: Float get() = rating.rate
    val reviews: Int get() = rating.count
}

/**
 * Rating model for product ratings from Fakestore API
 */
@Serializable
data class Rating(
    val rate: Float,
    val count: Int
)

/**
 * Cart item model for the shopping cart
 */
@Serializable
data class CartItem(
    val productId: Int,
    val quantity: Int
)

/**
 * Cart model representing the entire shopping cart
 */
@Serializable
data class Cart(
    val id: Int? = null,
    val userId: Int? = null,
    val date: String? = null,
    val products: List<CartItem>,
    val total: Double? = null
)
