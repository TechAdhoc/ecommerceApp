package com.example.ecommerceapp.data.remote

import com.example.ecommerceapp.data.model.AuthResponse
import com.example.ecommerceapp.data.model.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Authentication API service
 */
interface AuthApiService {
    
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<AuthResponse>
    
    @POST("auth/logout")
    suspend fun logout(): Response<Unit>
}
