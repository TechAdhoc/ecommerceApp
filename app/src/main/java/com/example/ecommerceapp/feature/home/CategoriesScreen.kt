package com.example.ecommerceapp.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.ecommerceapp.core.designsystem.theme.Theme
import com.example.ecommerceapp.core.designsystem.organisms.AppTopBar

@Composable
fun CategoriesScreen(
    navigateToProductDetail: (String) -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(title = "Categories", showBackButton = false)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Shop by Categories",
                style = Theme.titleLarge
            )
        }
    }
}
