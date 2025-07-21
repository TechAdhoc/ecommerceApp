package com.example.ecommerceapp.core.designsystem.templates

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.ecommerceapp.core.components.Loader
import com.example.ecommerceapp.core.designsystem.molecules.InfoBanner
import com.example.ecommerceapp.core.designsystem.molecules.InfoBannerType
import com.example.ecommerceapp.core.designsystem.organisms.AppTopBar
import com.example.ecommerceapp.core.designsystem.organisms.BottomNavItem
import com.example.ecommerceapp.core.designsystem.organisms.BottomNavigationBar
import com.example.ecommerceapp.core.designsystem.theme.Theme

/**
 * Standard screen template with top app bar and optional bottom navigation
 */
@Composable
fun StandardScreen(
    title: String,
    navController: NavHostController? = null,
    bottomNavItems: List<BottomNavItem>? = null,
    showBackButton: Boolean = false,
    showCartButton: Boolean = true,
    showSearchButton: Boolean = true,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onTryAgain: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = title,
                showBackButton = showBackButton,
                showCartButton = showCartButton,
                showSearchButton = showSearchButton,
                onBackClick = onBackClick,
                onSearchClick = onSearchClick,
                onCartClick = onCartClick
            )
        },
        bottomBar = {
            if (navController != null && bottomNavItems != null) {
                BottomNavigationBar(
                    navController = navController,
                    items = bottomNavItems
                )
            }
        },
        content = { paddingValues ->
            Surface(
                color = Theme.background,
                modifier = Modifier.fillMaxSize()
            ) {
                Column {
                    // Show loading or error banners at the top if needed
                    if (isLoading) {
                        InfoBanner(message = "Loading...", type = InfoBannerType.LOADING)
                    }
                    
                    if (errorMessage != null) {
                        InfoBanner(
                            message = errorMessage,
                            type = InfoBannerType.ERROR,
                            onDismiss = onTryAgain
                        )
                    }
                    
                    // Main content
                    Box(modifier = Modifier.padding(paddingValues)) {
                        content(paddingValues)
                        
                        // Overlay loader if needed
                        if (isLoading) {
                            Loader(isLoading = true)
                        }
                    }
                }
            }
        }
    )
}
