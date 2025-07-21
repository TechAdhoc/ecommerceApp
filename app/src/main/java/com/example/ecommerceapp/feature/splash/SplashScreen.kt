package com.example.ecommerceapp.feature.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.Text
import com.example.ecommerceapp.core.designsystem.theme.Theme
import com.example.ecommerceapp.core.designsystem.theme.Dimensions
import com.example.ecommerceapp.core.designsystem.theme.Primary
import com.example.ecommerceapp.feature.auth.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        label = "Splash Alpha Animation"
    ).value

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(1500) // Splash screen delay
        if (isLoggedIn) {
            navigateToHome()
        } else {
            navigateToLogin()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Primary),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.alpha(alphaAnimation)
        ) {
            // Replace R.drawable.app_logo with your actual logo resource
            // Here we're using a placeholder
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_report_image),
                contentDescription = "App Logo",
                modifier = Modifier.size(120.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
            
            Spacer(modifier = Modifier.height(Dimensions.spacing_16))
            
            Text(
                text = "ECommerce App",
                style = Theme.headlineLarge,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}
