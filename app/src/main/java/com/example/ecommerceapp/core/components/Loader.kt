package com.example.ecommerceapp.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

/**
 * Loading indicator component
 * @param isLoading Whether to show the loading indicator
 * @param fullScreen Whether to display as a fullscreen overlay (true) or inline (false)
 * @param modifier Modifier to be applied to the component
 */
@Composable
fun Loader(
    isLoading: Boolean,
    fullScreen: Boolean = false,
    modifier: Modifier = Modifier
) {
    if (isLoading) {
        Box(
            modifier = if (fullScreen) {
                modifier
                    .fillMaxSize()
                    .zIndex(10f) // Ensure it appears above other content when fullScreen
            } else {
                modifier.fillMaxSize()
            },
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp)
            )
        }
    }
}
