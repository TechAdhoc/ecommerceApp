package com.example.ecommerceapp.designsystem.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.design.Theme
import com.example.ecommerceapp.designsystem.atoms.BodyLarge
import com.example.ecommerceapp.designsystem.atoms.BodyMedium
import com.example.ecommerceapp.designsystem.atoms.TitleMedium
import com.example.ecommerceapp.designsystem.theme.Dimensions

/**
 * Card component that combines various atoms to create a standard card layout
 */
@Composable
fun EcommerceCard(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    description: String? = null,
    shape: Shape = MaterialTheme.shapes.medium,
    onClick: (() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        elevation = CardDefaults.cardElevation(defaultElevation = Dimensions.card_elevation),
        onClick = onClick ?: {},
        enabled = onClick != null
    ) {
        Column(
            modifier = Modifier.padding(Dimensions.spacing_16)
        ) {
            TitleMedium(
                text = title,
                modifier = Modifier.fillMaxWidth()
            )
            
            if (subtitle != null) {
                Spacer(modifier = Modifier.height(Dimensions.spacing_4))
                BodyLarge(
                    text = subtitle,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            if (description != null) {
                Spacer(modifier = Modifier.height(Dimensions.spacing_8))
                BodyMedium(
                    text = description,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            content?.let {
                Spacer(modifier = Modifier.height(Dimensions.spacing_16))
                it()
            }
        }
    }
}
