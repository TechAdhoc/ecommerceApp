package com.example.ecommerceapp.domain.usecase

import com.example.ecommerceapp.core.network.ApiResult
import com.example.ecommerceapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for user logout
 */
class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<ApiResult<Unit>> {
        return authRepository.logout()
    }
}
