package com.example.ecommerceapp.domain.usecase

import com.example.ecommerceapp.core.network.ApiResult
import com.example.ecommerceapp.data.model.AuthResponse
import com.example.ecommerceapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for user login
 */
class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, password: String): Flow<ApiResult<AuthResponse>> {
        return authRepository.login(email, password)
    }
}
