package com.example.ecommerceapp.domain.usecase

import com.example.ecommerceapp.core.network.ApiResult
import com.example.ecommerceapp.data.model.Product
import com.example.ecommerceapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case to get products list
 */
class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(limit: Int = 20): Flow<ApiResult<List<Product>>> {
        return productRepository.getProducts(limit)
    }

    fun getByCategory(category: String): Flow<ApiResult<List<Product>>> {
        return productRepository.getProductsByCategory(category)
    }

    fun getCategories(): Flow<ApiResult<List<String>>> {
        return productRepository.getCategories()
    }
}
