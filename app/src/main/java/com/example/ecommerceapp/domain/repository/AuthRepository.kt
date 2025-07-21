package com.example.ecommerceapp.domain.repository

import com.example.ecommerceapp.core.network.ApiResult
import com.example.ecommerceapp.data.model.AuthResponse
import com.example.ecommerceapp.data.model.LoginRequest
import kotlinx.coroutines.flow.Flow

/**
 * Auth repository interface
 */
interface AuthRepository {

    fun login(email: String, password: String): Flow<ApiResult<AuthResponse>>

    fun logout(): Flow<ApiResult<Unit>>


    fun handleSessionExpiry(): Flow<ApiResult<Unit>>

    fun isSessionValid(): Flow<Boolean>
}
