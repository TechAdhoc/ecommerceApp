package com.example.ecommerceapp.data.model

/**
 * Authentication response from login API
 */
data class AuthResponse(
    val token: String,
    val user: User
)

data class User(
    val id: String,
    val name: String,
    val email: String
)
