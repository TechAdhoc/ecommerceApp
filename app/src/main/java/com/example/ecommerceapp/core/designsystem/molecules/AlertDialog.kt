package com.example.ecommerceapp.core.designsystem.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ecommerceapp.core.designsystem.theme.Dimensions
import com.example.ecommerceapp.core.designsystem.theme.Theme

/**
 * A reusable alert dialog that can be used across the app.
 * 
 * @param title The title of the dialog
 * @param message The message text to display in the dialog
 * @param confirmButtonText Text for the confirmation button
 * @param dismissButtonText Optional text for the dismiss button. If null, dismiss button won't be shown
 * @param onConfirmClick Callback when confirm button is clicked
 * @param onDismissClick Optional callback when dismiss button is clicked or dialog is dismissed
 * @param properties DialogProperties for customizing dialog behavior
 */
@Composable
fun AppAlertDialog(
    title: String,
    message: String,
    confirmButtonText: String = "OK",
    dismissButtonText: String? = "Cancel",
    onConfirmClick: () -> Unit,
    onDismissClick: (() -> Unit)? = null,
    properties: DialogProperties = DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = true
    )
) {
    Dialog(
        onDismissRequest = { onDismissClick?.invoke() },
        properties = properties
    ) {
        Card(
            shape = ButtonDefaults.shape,
            colors = CardDefaults.cardColors(
                containerColor = Theme.surface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = Dimensions.card_elevation
            )
        ) {
            Column(
                modifier = Modifier.Companion.padding(Dimensions.spacing_20)
            ) {
                // Title
                Text(
                    text = title,
                    style = Theme.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.Companion.height(Dimensions.spacing_12))
                
                // Message
                Text(
                    text = message,
                    style = Theme.bodyLarge
                )
                
                Spacer(modifier = Modifier.Companion.height(Dimensions.spacing_24))
                
                // Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    if (dismissButtonText != null) {
                        Button(
                            onClick = { onDismissClick?.invoke() },
                            modifier = Modifier,
                            colors = ButtonDefaults.buttonColors()
                        ) {
                            Text(text = dismissButtonText)
                        }
                        
                        Spacer(modifier = Modifier.Companion.width(Dimensions.spacing_8))
                    }

                    Button(
                        onClick = onConfirmClick,
                        modifier = Modifier,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Theme.primary
                        )
                    ) {
                        Text(text = confirmButtonText)
                    }
                }
            }
        }
    }
}

/**
 * Progress dialog to show loading state
 */
@Composable
fun AppProgressDialog(
    message: String = "Please wait...",
    properties: DialogProperties = DialogProperties(
        dismissOnBackPress = false,
        dismissOnClickOutside = false
    )
) {
    Dialog(
        onDismissRequest = { },
        properties = properties
    ) {
        Card(
            shape = ButtonDefaults.shape,
            colors = CardDefaults.cardColors(
                containerColor = Theme.surface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = Dimensions.card_elevation
            )
        ) {
            Column(
                modifier = Modifier.Companion
                    .padding(Dimensions.spacing_20)
                    .fillMaxWidth()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.Companion
                        .padding(Dimensions.spacing_8)
                        .align(Alignment.CenterHorizontally),
                    color = Theme.primary
                )
                
                Spacer(modifier = Modifier.Companion.height(Dimensions.spacing_16))
                
                Text(
                    text = message,
                    style = Theme.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
