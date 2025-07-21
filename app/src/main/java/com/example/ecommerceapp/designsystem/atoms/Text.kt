package com.example.ecommerceapp.designsystem.atoms

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.ecommerceapp.design.Theme
import com.example.ecommerceapp.designsystem.theme.Dimensions

/**
 * Title text component with standardized styles
 */
@Composable
fun TitleLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Theme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        modifier = modifier,
        style = Theme.titleLarge,
        color = color,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow
    )
}

/**
 * Subtitle text component with standardized styles
 */
@Composable
fun TitleMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Theme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        modifier = modifier,
        style = Theme.titleMedium,
        color = color,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow
    )
}

/**
 * Body text component with standardized styles
 */
@Composable
fun BodyLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Theme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        modifier = modifier,
        style = Theme.typography.bodyLarge,
        color = color,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow
    )
}

/**
 * Small body text component with standardized styles
 */
@Composable
fun BodyMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Theme.onSurface,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        modifier = modifier,
        style = Theme.typography.bodyMedium,
        color = color,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow
    )
}

/**
 * Caption text component with standardized styles
 */
@Composable
fun Caption(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Theme.colorScheme.onSurface.copy(alpha = 0.7f),
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        modifier = modifier,
        style = Theme.typography.bodyMedium,
        color = color,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow
    )
}
