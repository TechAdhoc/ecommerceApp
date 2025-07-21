package com.example.ecommerceapp.core.designsystem.organisms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.ecommerceapp.core.designsystem.atoms.EcommerceIconButton
import com.example.ecommerceapp.core.designsystem.atoms.EcommercePrimaryButton
import com.example.ecommerceapp.core.designsystem.atoms.EcommerceTextButton
import com.example.ecommerceapp.core.designsystem.atoms.TitleLarge
import com.example.ecommerceapp.core.designsystem.molecules.FormField
import com.example.ecommerceapp.core.designsystem.molecules.InfoBanner
import com.example.ecommerceapp.core.designsystem.molecules.InfoBannerType
import com.example.ecommerceapp.core.designsystem.theme.Dimensions
import com.example.ecommerceapp.core.designsystem.theme.Theme

/**
 * Login form organism combines multiple molecules to create a complete login experience
 */
@Composable
fun LoginForm(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    emailError: String? = null,
    passwordError: String? = null
) {
    val focusManager = LocalFocusManager.current
    var passwordVisible by remember { mutableStateOf(false) }
    
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimensions.card_elevation),
        colors = CardDefaults.cardColors(
            containerColor = Theme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimensions.spacing_24, vertical = Dimensions.spacing_32)
        ) {
            TitleLarge(
                text = "Login",
                modifier = Modifier.padding(bottom = Dimensions.spacing_24)
            )
            
            // Error message if any
            errorMessage?.let {
                InfoBanner(
                    message = it,
                    type = InfoBannerType.ERROR,
                    modifier = Modifier.padding(bottom = Dimensions.spacing_16)
                )
            }
            
            // Email field
            FormField(
                value = email,
                onValueChange = onEmailChange,
                label = "Email",
                isError = emailError != null,
                errorMessage = emailError,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon"
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
            
            Spacer(modifier = Modifier.Companion.height(Dimensions.spacing_16))
            
            // Password field
            FormField(
                value = password,
                onValueChange = onPasswordChange,
                label = "Password",
                isError = passwordError != null,
                errorMessage = passwordError,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon"
                    )
                },
                trailingIcon = {
                    EcommerceIconButton(
                        imageVector = if (passwordVisible) Icons.Default.Lock else Icons.Default.Lock,
                        onClick = { passwordVisible = !passwordVisible },
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                },
                visualTransformation = if (passwordVisible)
                    VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        onLoginClick()
                    }
                )
            )
            
            Spacer(modifier = Modifier.Companion.height(Dimensions.spacing_8))
            
            // Forgot password
            EcommerceTextButton(
                onClick = onForgotPasswordClick,
                text = "Forgot Password?",
                modifier = Modifier.padding(vertical = Dimensions.spacing_8)
            )
            
            Spacer(modifier = Modifier.Companion.height(Dimensions.spacing_24))
            
            // Login button
            EcommercePrimaryButton(
                onClick = onLoginClick,
                enabled = !isLoading
            ) {
                Text(
                    text = if (isLoading) "Logging in..." else "Login"
                )
            }
        }
    }
}
