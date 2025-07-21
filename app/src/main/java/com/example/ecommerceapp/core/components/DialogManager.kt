package com.example.ecommerceapp.core.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.ecommerceapp.core.designsystem.molecules.AppAlertDialog
import com.example.ecommerceapp.core.designsystem.molecules.AppProgressDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * Sealed class representing different types of dialogs
 */
sealed class DialogType {
    data class Alert(
        val title: String,
        val message: String,
        val confirmButtonText: String = "OK",
        val dismissButtonText: String? = "Cancel",
        val onConfirmClick: () -> Unit,
        val onDismissClick: (() -> Unit)? = null
    ) : DialogType()
    
    data class Progress(val message: String = "Please wait...") : DialogType()
    
    object None : DialogType()
}

/**
 * Class that manages dialogs across the application
 */
class DialogManager(private val coroutineScope: CoroutineScope) {
    // Flow to emit dialog events
    private val _dialogFlow = MutableSharedFlow<DialogType>()
    val dialogFlow: SharedFlow<DialogType> = _dialogFlow.asSharedFlow()
    
    // Show alert dialog
    fun showAlert(
        title: String,
        message: String,
        confirmButtonText: String = "OK",
        dismissButtonText: String? = "Cancel",
        onConfirmClick: () -> Unit,
        onDismissClick: (() -> Unit)? = null
    ) {
        coroutineScope.launch {
            _dialogFlow.emit(
                DialogType.Alert(
                    title = title,
                    message = message,
                    confirmButtonText = confirmButtonText,
                    dismissButtonText = dismissButtonText,
                    onConfirmClick = {
                        onConfirmClick()
                        // Dismiss dialog after click
                        coroutineScope.launch { _dialogFlow.emit(DialogType.None) }
                    },
                    onDismissClick = {
                        onDismissClick?.invoke()
                        // Dismiss dialog
                        coroutineScope.launch { _dialogFlow.emit(DialogType.None) }
                    }
                )
            )
        }
    }
    
    // Show progress dialog
    fun showProgress(message: String = "Please wait...") {
        coroutineScope.launch {
            _dialogFlow.emit(DialogType.Progress(message))
        }
    }
    
    // Hide any dialog
    fun hideDialog() {
        coroutineScope.launch {
            _dialogFlow.emit(DialogType.None)
        }
    }
}

/**
 * Composable function to create and remember a DialogManager instance
 */
@Composable
fun rememberDialogManager(): DialogManager {
    val scope = rememberCoroutineScope()
    return remember { DialogManager(scope) }
}

/**
 * Composable that handles showing dialogs based on the DialogManager state
 */
@Composable
fun DialogHandler(dialogManager: DialogManager) {
    val dialogState = remember { mutableStateOf<DialogType>(DialogType.None) }

    LaunchedEffect(dialogManager) {
        dialogManager.dialogFlow.collect { dialogType ->
            dialogState.value = dialogType
        }
    }

    when (val state = dialogState.value) {
        is DialogType.Alert -> {
            AppAlertDialog(
                title = state.title,
                message = state.message,
                confirmButtonText = state.confirmButtonText,
                dismissButtonText = state.dismissButtonText,
                onConfirmClick = state.onConfirmClick,
                onDismissClick = state.onDismissClick
            )
        }
        is DialogType.Progress -> {
            AppProgressDialog(message = state.message)
        }
        DialogType.None -> {
            // No dialog to show
        }
    }
}
