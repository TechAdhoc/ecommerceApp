package com.example.ecommerceapp.data.remote

import com.example.ecommerceapp.data.model.Cart
import com.example.ecommerceapp.data.model.Product
import retrofit2.Response
import retrofit2.http.*

/**
 * Product API service for FakeStoreAPI (https://fakestoreapi.com/docs)
 */
interface ProductApiService {
    
    // Product endpoints
    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int = 20
    ): Response<List<Product>>
    
    @GET("products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String
    ): Response<List<Product>>

    @GET("products/categories")
    suspend fun getCategories(): Response<List<String>>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Int): Response<Product>

    @POST("products")
    suspend fun addProduct(@Body product: Product): Response<Product>

    @PUT("products/{id}")
    suspend fun updateProduct(
        @Path("id") id: Int,
        @Body product: Product
    ): Response<Product>

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id: Int): Response<Product>

    // Cart endpoints
    @GET("carts/user/{userId}")
    suspend fun getUserCart(@Path("userId") userId: Int): Response<List<Cart>>

    @POST("carts")
    suspend fun createCart(@Body cart: Cart): Response<Cart>

    @PUT("carts/{id}")
    suspend fun updateCart(
        @Path("id") id: Int,
        @Body cart: Cart
    ): Response<Cart>

    @DELETE("carts/{id}")
    suspend fun deleteCart(@Path("id") id: Int): Response<Cart>
}
