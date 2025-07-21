package com.example.ecommerceapp.navigation

sealed class Screen(val route: String) {
    // Auth routes
    object Splash : Screen("splash_screen")
    object Login : Screen("login_screen")
    
    // Main routes
    object Home : Screen("home_screen")
    object Products : Screen("products_screen")
    object Categories : Screen("categories_screen")
    object Cart : Screen("cart_screen")
    object Profile : Screen("profile_screen")
    
    // Detail routes
    object ProductDetail : Screen("product_detail_screen/{productId}") {
        fun createRoute(productId: String) = "product_detail_screen/$productId"
    }
}
