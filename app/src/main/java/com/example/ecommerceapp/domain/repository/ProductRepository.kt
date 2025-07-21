package com.example.ecommerceapp.domain.repository

import com.example.ecommerceapp.core.network.ApiResult
import com.example.ecommerceapp.data.model.Cart
import com.example.ecommerceapp.data.model.Product
import kotlinx.coroutines.flow.Flow

/**
 * Product repository interface for FakeStoreAPI
 */
interface ProductRepository {

    fun getProducts(limit: Int = 20): Flow<ApiResult<List<Product>>>

    fun getProductsByCategory(category: String): Flow<ApiResult<List<Product>>>


    fun getCategories(): Flow<ApiResult<List<String>>>

    fun getProductById(id: Int): Flow<ApiResult<Product>>

    fun addProduct(product: Product): Flow<ApiResult<Product>>

    fun updateProduct(id: Int, product: Product): Flow<ApiResult<Product>>

    fun deleteProduct(id: Int): Flow<ApiResult<Product>>

    fun getUserCart(userId: Int): Flow<ApiResult<List<Cart>>>


    fun createCart(cart: Cart): Flow<ApiResult<Cart>>


    fun updateCart(id: Int, cart: Cart): Flow<ApiResult<Cart>>

    fun deleteCart(id: Int): Flow<ApiResult<Cart>>
}
