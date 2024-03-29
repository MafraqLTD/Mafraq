package com.mafraq.presentation.design.components.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


fun SnackbarHostState.showSnackbar(
    message: String,
    actionLabel: String? = null,
    withDismissAction: Boolean = false,
    duration: SnackbarDuration = actionLabel?.let { SnackbarDuration.Indefinite }
        ?: SnackbarDuration.Short,
    onAccept: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    CoroutineScope(Dispatchers.IO).let { scope ->
        scope.launch {
            isVisible = true
            showSnackbar(
                message = message,
                actionLabel = actionLabel,
                withDismissAction = withDismissAction,
                duration = duration
            ).let {
                when (it) {
                    SnackbarResult.Dismissed -> onDismiss()
                    SnackbarResult.ActionPerformed -> onAccept()
                }
            }
            isVisible = false
            scope.cancel()
        }
    }
}
