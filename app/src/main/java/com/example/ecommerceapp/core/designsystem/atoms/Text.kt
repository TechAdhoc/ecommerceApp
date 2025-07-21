package com.example.ecommerceapp.core.designsystem.atoms

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.ecommerceapp.core.designsystem.theme.Theme

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
        style = Theme.bodyLarge,
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
        style = Theme.bodyMedium,
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
    color: Color = Theme.onSurface.copy(alpha = 0.7f),
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        modifier = modifier,
        style = Theme.bodyMedium,
        color = color,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow
    )
}
