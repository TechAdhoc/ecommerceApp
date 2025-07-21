package com.example.ecommerceapp.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.core.components.AppTopBar
import com.example.ecommerceapp.core.components.ErrorDialog
import com.example.ecommerceapp.core.components.ErrorState
import com.example.ecommerceapp.core.components.NetworkErrorScreen
import com.example.ecommerceapp.core.components.Loader
import com.example.ecommerceapp.core.network.ApiResult
import com.example.ecommerceapp.core.network.NetworkConnectivityManager
import com.example.ecommerceapp.core.network.observeConnectivityAsState
import com.example.ecommerceapp.design.Theme
import com.example.ecommerceapp.designsystem.atoms.TitleLarge
import com.example.ecommerceapp.designsystem.atoms.BodyLarge
import com.example.ecommerceapp.designsystem.organisms.BottomNavItem
import com.example.ecommerceapp.designsystem.organisms.BottomNavigationBar
import com.example.ecommerceapp.designsystem.organisms.ProductCarousel
import com.example.ecommerceapp.designsystem.theme.Dimensions
import com.example.ecommerceapp.feature.home.viewmodel.HomeViewModel
import com.example.ecommerceapp.navigation.Screen

@Composable
fun HomeScreen(
    navigateToProductDetail: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val featuredProducts by viewModel.featuredProducts.collectAsState()
    val newArrivals by viewModel.newArrivals.collectAsState()
    val popularProducts by viewModel.popularProducts.collectAsState()

    // Combined loading state - true if any of the API calls is loading
    val isLoading by remember {
        derivedStateOf {
            featuredProducts is ApiResult.Loading ||
            newArrivals is ApiResult.Loading ||
            popularProducts is ApiResult.Loading
        }
    }

    // Shared error dialog state
    var currentErrorState by remember { mutableStateOf<ErrorState?>(null) }

    // Monitor for errors from any product section
    LaunchedEffect(featuredProducts, newArrivals, popularProducts) {
        when {
            featuredProducts is ApiResult.Error -> {
                val error = featuredProducts as ApiResult.Error
                currentErrorState = ErrorState(
                    message = error.message,
                    onRetry = { viewModel.clearFeaturedProductsError() },
                    onDismiss = { currentErrorState = null }
                )
            }
            newArrivals is ApiResult.Error && currentErrorState == null -> {
                val error = newArrivals as ApiResult.Error
                currentErrorState = ErrorState(
                    message = error.message,
                    onRetry = { viewModel.clearNewArrivalsError() },
                    onDismiss = { currentErrorState = null }
                )
            }
            popularProducts is ApiResult.Error && currentErrorState == null -> {
                val error = popularProducts as ApiResult.Error
                currentErrorState = ErrorState(
                    message = error.message,
                    onRetry = { viewModel.clearPopularProductsError() },
                    onDismiss = { currentErrorState = null }
                )
            }
        }
    }

    // Show the shared error dialog
    currentErrorState?.let { errorState ->
        ErrorDialog(
            errorMessage = errorState.message,
            onRetry = {
                errorState.onRetry()
                currentErrorState = null
            },
            onDismiss = errorState.onDismiss
        )
    }

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    val bottomNavItems = remember {
        listOf(
            BottomNavItem(
                route = Screen.Home.route,
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            ),
           /* BottomNavItem(
                route = Screen.Categories.route,
                title = "Categories",
                selectedIcon = Icons.Filled.Category,
                unselectedIcon = Icons.Outlined.Category
            ),
            BottomNavItem(
                route = Screen.Cart.route,
                title = "Cart",
                selectedIcon = Icons.Filled.ShoppingCart,
                unselectedIcon = Icons.Outlined.ShoppingCart
            ),*/
            BottomNavItem(
                route = Screen.Profile.route,
                title = "Profile",
                selectedIcon = Icons.Filled.Person,
                unselectedIcon = Icons.Outlined.Person
            )
        )
    }
    
    val context = LocalContext.current
    val networkConnectivityManager = remember { NetworkConnectivityManager(context) }
    val isConnected by networkConnectivityManager.observeConnectivityAsState()

    if (!isConnected) {
        NetworkErrorScreen(
            onRetry = { viewModel.loadHomeData() }
        )
    } else {
        Scaffold(
            topBar = {
                // Flipkart-inspired top bar with search
                Surface(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        AppTopBar(
                            title = "ShopEase",
                            onBackClick = null, // Use onBackClick instead of showBackButton
                            actions = {
                                IconButton(onClick = { /* Navigate to search */ }) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search",
                                        tint = Color.White
                                    )
                                }
                            }
                        )

                        // Search bar placeholder (similar to Flipkart)
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 2.dp
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Search for products, brands and more",
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            },
            bottomBar = {
                BottomNavigationBar(
                    navController = navController,
                    items = bottomNavItems
                )
            }
        ) { paddingValues ->
            // Single loader for all API calls
            Box(modifier = Modifier.fillMaxSize()) {
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .zIndex(10f),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(48.dp),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                // Content
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                        .background(Color(0xFFF1F3F6)) // Light gray background like Flipkart
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    // Featured Products section with square shape
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(0.dp), // Square shape (0dp corner radius)
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp // Increased elevation for more prominence
                        )
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = "Featured Products",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                            )

                            if (featuredProducts is ApiResult.Success) {
                                ProductCarousel(
                                    title = "",  // Title already shown above
                                    products = (featuredProducts as ApiResult.Success).data,
                                    onProductClick = { product -> navigateToProductDetail(product.id.toString()) },
                                    onAddToCartClick = { /* Add to cart */ }
                                )
                            } else if (!isLoading && featuredProducts !is ApiResult.Error) {
                                BodyLarge(
                                    text = "No featured products available",
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // New Arrivals section with square shape
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(0.dp), // Square shape (0dp corner radius)
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp // Increased elevation for more prominence
                        )
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = "New Arrivals",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                            )

                            if (newArrivals is ApiResult.Success) {
                                ProductCarousel(
                                    title = "",  // Title already shown above
                                    products = (newArrivals as ApiResult.Success).data,
                                    onProductClick = { product -> navigateToProductDetail(product.id.toString()) },
                                    onAddToCartClick = { /* Add to cart */ }
                                )
                            } else if (!isLoading && newArrivals !is ApiResult.Error) {
                                BodyLarge(
                                    text = "No new arrivals available",
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Popular Products section with square shape
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(0.dp), // Square shape (0dp corner radius)
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp // Increased elevation for more prominence
                        )
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = "Popular Products",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                            )

                            if (popularProducts is ApiResult.Success) {
                                ProductCarousel(
                                    title = "",  // Title already shown above
                                    products = (popularProducts as ApiResult.Success).data,
                                    onProductClick = { product -> navigateToProductDetail(product.id.toString()) },
                                    onAddToCartClick = { /* Add to cart */ }
                                )
                            } else if (!isLoading && popularProducts !is ApiResult.Error) {
                                BodyLarge(
                                    text = "No popular products available",
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
