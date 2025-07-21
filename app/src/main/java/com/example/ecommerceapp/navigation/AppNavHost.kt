package com.example.ecommerceapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ecommerceapp.feature.auth.LoginScreen
import com.example.ecommerceapp.feature.auth.viewmodel.AuthViewModel
import com.example.ecommerceapp.feature.home.CartScreen
import com.example.ecommerceapp.feature.home.CategoriesScreen
import com.example.ecommerceapp.feature.home.HomeScreen
import com.example.ecommerceapp.feature.home.ProductDetailScreen
import com.example.ecommerceapp.feature.home.ProfileScreen
import com.example.ecommerceapp.feature.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState(initial = false)

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Authentication flow
        composable(route = Screen.Splash.route) {
            SplashScreen(
                navigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                navigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(route = Screen.Login.route) {
            LoginScreen(
                navigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        
        // Main app flow
        composable(route = Screen.Home.route) {
            HomeScreen(
                navigateToProductDetail = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                }
            )
        }
        
        composable(route = Screen.Categories.route) {
            CategoriesScreen(
                navigateToProductDetail = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                }
            )
        }
        
        composable(route = Screen.Cart.route) {
            CartScreen()
        }
        
        composable(route = Screen.Profile.route) {
            ProfileScreen(
                onLogout = {
                    authViewModel.logout()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
        
        // Product detail screen with productId parameter
        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductDetailScreen(
                productId = productId,
                navigateUp = { navController.popBackStack() }
            )
        }
    }
    
    // Handle session expiry and logout
    authViewModel.logoutEvent.collectAsState(initial = false).value.let { shouldLogout ->
        if (shouldLogout) {
            navController.navigate(Screen.Login.route) {
                popUpTo(0) { inclusive = true }
            }
            authViewModel.resetLogoutEvent()
        }
    }
}
