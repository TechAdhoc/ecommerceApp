package com.example.ecommerceapp.designsystem.organisms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.design.Theme
import com.example.ecommerceapp.designsystem.atoms.EcommerceIconButton
import com.example.ecommerceapp.designsystem.atoms.TitleMedium
import com.example.ecommerceapp.designsystem.theme.Dimensions

/**
 * Top app bar organism with various configurations
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Theme.primary,
    contentColor: Color = Theme.onPrimary,
    showBackButton: Boolean = false,
    showCartButton: Boolean = true,
    showSearchButton: Boolean = true,
    onBackClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    additionalActions: @Composable (RowScope.() -> Unit)? = null
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TopAppBar(
            title = { 
                TitleMedium(
                    text = title,
                    color = contentColor
                )
            },
            navigationIcon = {
                if (showBackButton) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = contentColor
                        )
                    }
                } else {
                    // Optional: Show a menu/drawer icon or logo here
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = contentColor
                        )
                    }
                }
            },
            actions = {
                // Search icon
                if (showSearchButton) {
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = contentColor
                        )
                    }
                }
                
                // Cart icon
                if (showCartButton) {
                    IconButton(onClick = onCartClick) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Shopping Cart",
                            tint = contentColor
                        )
                    }
                }
                
                // Additional custom actions
                additionalActions?.invoke(this)
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = backgroundColor,
                titleContentColor = contentColor,
                navigationIconContentColor = contentColor,
                actionIconContentColor = contentColor
            )
        )
    }
}
