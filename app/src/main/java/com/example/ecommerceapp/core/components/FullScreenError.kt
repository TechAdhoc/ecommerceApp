package com.example.ecommerceapp.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.core.designsystem.theme.Theme
import com.example.ecommerceapp.core.designsystem.atoms.EcommerceSurface

/**
 * Full screen error component that displays different error states
 * with a retry button
 */
@Composable
fun FullScreenError(
    title: String,
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    imageResId: Int = R.drawable.ic_error_network
) {
    EcommerceSurface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Error Illustration",
                modifier = Modifier.size(120.dp)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = title,
                style = Theme.headlineSmall,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = message,
                style = Theme.bodyLarge,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Button(
                onClick = onRetry,
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text(text = "Try Again")
            }
        }
    }
}

/**
 * Network error specific implementation of the FullScreenError
 */
@Composable
fun NetworkErrorScreen(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    FullScreenError(
        title = "No Internet Connection",
        message = "Please check your internet connection and try again",
        onRetry = onRetry,
        modifier = modifier,
        imageResId = R.drawable.ic_error_network
    )
}

/**
 * API error specific implementation of the FullScreenError
 */
@Composable
fun ApiErrorScreen(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    FullScreenError(
        title = "Something Went Wrong",
        message = message,
        onRetry = onRetry,
        modifier = modifier,
        imageResId = R.drawable.ic_error_server
    )
}
