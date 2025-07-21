package com.example.ecommerceapp.designsystem.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Shapes for the design system.
 * Defines shape styles for different UI components.
 */
val EcommerceShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp)
)

// Additional custom shapes
val PillShape = RoundedCornerShape(50)
val CircularShape = CircleShape
val RoundedTopShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
val RoundedBottomShape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
val CutShape = CutCornerShape(8.dp)
