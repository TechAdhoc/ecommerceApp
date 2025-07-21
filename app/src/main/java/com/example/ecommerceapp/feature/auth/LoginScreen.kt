package com.example.ecommerceapp.feature.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ecommerceapp.core.components.Loader
import com.example.ecommerceapp.core.network.ApiResult
import com.example.ecommerceapp.designsystem.atoms.TitleLarge
import com.example.ecommerceapp.designsystem.organisms.LoginForm
import com.example.ecommerceapp.designsystem.templates.CenteredFormTemplate
import com.example.ecommerceapp.designsystem.theme.Dimensions
import com.example.ecommerceapp.feature.auth.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    
    val loginState by viewModel.loginState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    
    // Handle UI events
    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is AuthViewModel.AuthUiEvent.LoginSuccess -> {
                    navigateToHome()
                }
                is AuthViewModel.AuthUiEvent.Error -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                else -> {} // Handle other events if needed
            }
        }
    }
    
    // Function to validate inputs
    fun validateInputs(): Boolean {
        var isValid = true
        
        if (email.isBlank()) {
            emailError = "Email is required"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Enter a valid email"
            isValid = false
        } else {
            emailError = null
        }
        
        if (password.isBlank()) {
            passwordError = "Password is required"
            isValid = false
        } else if (password.length < 6) {
            passwordError = "Password must be at least 6 characters"
            isValid = false
        } else {
            passwordError = null
        }
        
        return isValid
    }
    
    // Handle login click
    fun handleLoginClick() {
        if (validateInputs()) {
            viewModel.login(email, password)
        }
    }

    CenteredFormTemplate(
        topContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                TitleLarge(
                    text = "Welcome to ECommerce",
                    color = androidx.compose.ui.graphics.Color.White
                )
            }
            
            Spacer(modifier = Modifier.height(Dimensions.spacing_40))
        }
    ) {
        LoginForm(
            email = email,
            onEmailChange = { email = it; emailError = null },
            password = password,
            onPasswordChange = { password = it; passwordError = null },
            onLoginClick = ::handleLoginClick,
            onForgotPasswordClick = { /* Handle forgot password */ },
            isLoading = loginState is ApiResult.Loading,
            errorMessage = if (loginState is ApiResult.Error) (loginState as ApiResult.Error).message else null,
            emailError = emailError,
            passwordError = passwordError
        )
    }
}
