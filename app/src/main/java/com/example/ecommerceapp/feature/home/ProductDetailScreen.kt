package com.example.ecommerceapp.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.ecommerceapp.design.Theme
import com.example.ecommerceapp.designsystem.organisms.AppTopBar

@Composable
fun ProductDetailScreen(
    productId: String,
    navigateUp: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Product Detail",
                showBackButton = true,
                onBackClick = navigateUp
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Product Detail for ID: $productId",
                style = Theme.titleLarge
            )
        }
    }
}
