package com.example.ecommerceapp.feature.auth.state

// Login form state
data class LoginFormState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

// User state
data class UserState(
    val isLoggedIn: Boolean = false,
    val name: String = "",
    val email: String = "",
    val userId: String = ""
)
