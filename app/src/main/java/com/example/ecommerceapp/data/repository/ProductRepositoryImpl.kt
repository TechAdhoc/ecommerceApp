package com.example.ecommerceapp.data.repository

import com.example.ecommerceapp.core.network.ApiResult
import com.example.ecommerceapp.core.network.safeApiCall
import com.example.ecommerceapp.data.model.Cart
import com.example.ecommerceapp.data.model.Product
import com.example.ecommerceapp.data.remote.ProductApiService
import com.example.ecommerceapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of ProductRepository for FakeStoreAPI
 */
class ProductRepositoryImpl @Inject constructor(
    private val productApiService: ProductApiService
) : ProductRepository {
    
    override fun getProducts(limit: Int): Flow<ApiResult<List<Product>>> {
        return safeApiCall {
            productApiService.getProducts(limit)
        }
    }

    override fun getProductsByCategory(category: String): Flow<ApiResult<List<Product>>> {
        return safeApiCall {
            productApiService.getProductsByCategory(category)
        }
    }
    
    override fun getCategories(): Flow<ApiResult<List<String>>> {
        return safeApiCall {
            productApiService.getCategories()
        }
    }

    override fun getProductById(id: Int): Flow<ApiResult<Product>> {
        return safeApiCall {
            productApiService.getProduct(id)
        }
    }

    override fun addProduct(product: Product): Flow<ApiResult<Product>> {
        return safeApiCall {
            productApiService.addProduct(product)
        }
    }

    override fun updateProduct(id: Int, product: Product): Flow<ApiResult<Product>> {
        return safeApiCall {
            productApiService.updateProduct(id, product)
        }
    }

    override fun deleteProduct(id: Int): Flow<ApiResult<Product>> {
        return safeApiCall {
            productApiService.deleteProduct(id)
        }
    }

    override fun getUserCart(userId: Int): Flow<ApiResult<List<Cart>>> {
        return safeApiCall {
            productApiService.getUserCart(userId)
        }
    }

    override fun createCart(cart: Cart): Flow<ApiResult<Cart>> {
        return safeApiCall {
            productApiService.createCart(cart)
        }
    }

    override fun updateCart(id: Int, cart: Cart): Flow<ApiResult<Cart>> {
        return safeApiCall {
            productApiService.updateCart(id, cart)
        }
    }

    override fun deleteCart(id: Int): Flow<ApiResult<Cart>> {
        return safeApiCall {
            productApiService.deleteCart(id)
        }
    }
}
