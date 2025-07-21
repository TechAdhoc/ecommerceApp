package com.example.ecommerceapp.designsystem.organisms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.ecommerceapp.core.components.ErrorDialog
import com.example.ecommerceapp.core.components.Loader
import com.example.ecommerceapp.core.network.ApiResult
import com.example.ecommerceapp.data.model.Product
import com.example.ecommerceapp.designsystem.atoms.TitleLarge
import com.example.ecommerceapp.designsystem.molecules.InfoBanner
import com.example.ecommerceapp.designsystem.molecules.InfoBannerType
import com.example.ecommerceapp.designsystem.molecules.LoadingBanner
import com.example.ecommerceapp.designsystem.molecules.ProductItemCard
import com.example.ecommerceapp.designsystem.theme.Dimensions

/**
 * ProductGrid organism for displaying a grid of products
 */
@Composable
fun ProductGrid(
    products: List<Product>,
    onProductClick: (Product) -> Unit,
    onAddToCartClick: (Product) -> Unit,
    modifier: Modifier = Modifier,
    columns: Int = 2,
    contentPadding: PaddingValues = PaddingValues(Dimensions.spacing_16)
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = modifier.fillMaxWidth(),
        contentPadding = contentPadding
    ) {
        items(products) { product ->
            ProductItemCard(
                name = product.name,
                price = product.price,
                imageUrl = product.imageUrl,
                rating = product.rate,  // Use rate instead of rating to fix type mismatch
                reviews = product.reviews,
                onProductClick = { onProductClick(product) },
                onAddToCartClick = { onAddToCartClick(product) },
                modifier = Modifier.padding(Dimensions.spacing_8)
            )
        }
    }
}

/**
 * ProductCarousel organism for displaying a horizontal list of products
 */
@Composable
fun ProductCarousel(
    title: String,
    products: List<Product>,
    onProductClick: (Product) -> Unit,
    onAddToCartClick: (Product) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = Dimensions.spacing_16)
) {
    Column(modifier = modifier) {
        TitleLarge(
            text = title,
            modifier = Modifier.padding(
                start = Dimensions.spacing_16,
                end = Dimensions.spacing_16,
                bottom = Dimensions.spacing_8
            )
        )
        
        LazyRow(
            contentPadding = contentPadding
        ) {
            items(products) { product ->
                ProductItemCard(
                    name = product.name,
                    price = product.price,
                    imageUrl = product.imageUrl,
                    rating = product.rate,  // Fix: use rate instead of rating
                    reviews = product.reviews,
                    onProductClick = { onProductClick(product) },
                    onAddToCartClick = { onAddToCartClick(product) },
                    modifier = Modifier
                        .width(180.dp)
                        .padding(Dimensions.spacing_8)
                )
            }
        }
    }
}

/**
 * ProductListWithState organism that handles loading, error and success states for product listings
 */
@Composable
fun <T> ProductListWithState(
    state: ApiResult<List<T>>,
    onRetry: () -> Unit,
    onDismissError: () -> Unit,
    emptyContent: @Composable () -> Unit,
    content: @Composable (List<T>) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is ApiResult.Loading -> {
                // Display loader with proper Z-index to ensure visibility
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(10f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
            
            is ApiResult.Error -> {
                // Don't show error dialog here
                // The parent component will handle error display via the shared dialog
                // Just call the callbacks to notify the parent about the error
                LaunchedEffect(state) {
                    // Notify parent about the error (will be used to manage shared dialog)
                    onDismissError()
                }

                // Show fallback content
                emptyContent()
            }
            
            is ApiResult.Success -> {
                if (state.data.isEmpty()) {
                    emptyContent()
                } else {
                    content(state.data)
                }
            }

            ApiResult.Initial -> {
                // Initial state - show empty content
                emptyContent()
            }
        }
    }
}
