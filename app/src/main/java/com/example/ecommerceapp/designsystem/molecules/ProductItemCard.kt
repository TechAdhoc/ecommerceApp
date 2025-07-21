package com.example.ecommerceapp.designsystem.molecules

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ecommerceapp.design.Theme
import com.example.ecommerceapp.designsystem.atoms.Caption
import com.example.ecommerceapp.designsystem.atoms.EcommercePrimaryButton
import com.example.ecommerceapp.designsystem.atoms.TitleMedium
import com.example.ecommerceapp.designsystem.theme.Dimensions
import kotlin.math.ceil
import kotlin.math.floor

/**
 * Product item card molecule for displaying product information in a list
 */
@Composable
fun ProductItemCard(
    name: String,
    price: Double,
    imageUrl: String,
    modifier: Modifier = Modifier,
    discountPrice: Double? = null,
    rating: Float? = null,
    reviews: Int? = null,
    onProductClick: () -> Unit = {},
    onAddToCartClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.clickable(onClick = onProductClick), // Make the whole card clickable
        shape = RoundedCornerShape(0.dp), // Square shape
        colors = CardDefaults.cardColors(
            containerColor = Theme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column {
            // Image displayed with Coil's AsyncImage
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
            )

            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                // Title
                TitleMedium(
                    text = name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(Dimensions.spacing_8))
                
                // Price
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (discountPrice != null) {
                        Text(
                            text = "$${String.format("%.2f", discountPrice)}",
                            style = Theme.typography.bodySmall,
                            color = Theme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                        
                        Spacer(modifier = Modifier.width(Dimensions.spacing_8))
                        
                        Text(
                            text = "$${String.format("%.2f", price)}",
                            style = Theme.typography.bodySmall,
                            color = Theme.colorScheme.onSurface.copy(alpha = 0.6f),
                            textDecoration = TextDecoration.LineThrough
                        )
                    } else {
                        Text(
                            text = "$${String.format("%.2f", price)}",
                            style = Theme.typography.titleMedium,
                            color = Theme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                
                if (rating != null) {
                    Spacer(modifier = Modifier.height(Dimensions.spacing_8))
                    
                    // Rating
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RatingBar(
                            rating = rating,
                            modifier = Modifier.height(16.dp)
                        )
                        
                        if (reviews != null) {
                            Spacer(modifier = Modifier.width(Dimensions.spacing_4))
                            Caption(text = "($reviews)")
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(Dimensions.spacing_12))

                EcommercePrimaryButton(
                    onClick = onAddToCartClick
                ) {
                    Text("Add to Cart")
                }
            }
        }
    }
}

/**
 * Rating bar component for displaying star ratings
 */
@Composable
fun RatingBar(
    rating: Float,
    modifier: Modifier = Modifier,
    stars: Int = 5,
    starsColor: Color = Color(0xFFFFC107)
) {
    Row(modifier = modifier) {
        for (i in 1..stars) {
            val starIcon = when {
                i <= floor(rating) -> Icons.Default.Star
                i == ceil(rating).toInt() && i - rating < 1 -> Icons.Default.Star
                else -> Icons.Default.Star
            }
            
            Icon(
                imageVector = starIcon,
                contentDescription = "Star $i",
                tint = starsColor,
                modifier = Modifier.padding(end = 2.dp)
            )
        }
    }
}
