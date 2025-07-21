package com.example.ecommerceapp.data.repository

import com.example.ecommerceapp.core.network.ApiResult
import com.example.ecommerceapp.core.network.safeApiCall
import com.example.ecommerceapp.core.session.SessionManager
import com.example.ecommerceapp.data.model.AuthResponse
import com.example.ecommerceapp.data.model.LoginRequest
import com.example.ecommerceapp.data.remote.AuthApiService
import com.example.ecommerceapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Implementation of AuthRepository
 */
class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService,
    private val sessionManager: SessionManager
) : AuthRepository {
    
    override fun login(email: String, password: String): Flow<ApiResult<AuthResponse>> {
        return safeApiCall {
            authApiService.login(LoginRequest(email, password))
        }.onEach { result ->
            if (result is ApiResult.Success) {
                // Save session data on successful login
                result.data.let { authResponse ->
                    sessionManager.saveSession(
                        token = authResponse.token,
                        userId = authResponse.user.id,
                        name = authResponse.user.name,
                        email = authResponse.user.email
                    )
                }
            }
        }
    }
    
    override fun logout(): Flow<ApiResult<Unit>> {
        return safeApiCall {
            authApiService.logout()
        }.onEach { result ->
            // Clear session regardless of API response
            sessionManager.logout()
        }
    }

    override fun handleSessionExpiry(): Flow<ApiResult<Unit>> {
        return flow {
            // Clear session data when session expires
            sessionManager.logout()
            emit(ApiResult.Success(Unit))
        }
    }

    override fun isSessionValid(): Flow<Boolean> {
        // Simply forward the isLoggedIn flow from sessionManager
        return sessionManager.isLoggedIn()
    }
}
