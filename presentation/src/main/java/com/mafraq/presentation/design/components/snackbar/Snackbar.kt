package com.mafraq.presentation.design.components.snackbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.utils.extensions.optionalComposable


@Composable
fun Snackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
    actionOnNewLine: Boolean = false,
    shape: Shape = SnackbarDefaults.shape,
    containerColor: Color = SnackbarDefaults.color,
    contentColor: Color = SnackbarDefaults.contentColor,
    actionColor: Color = SnackbarDefaults.actionColor,
    actionContentColor: Color = SnackbarDefaults.actionContentColor,
    dismissActionContentColor: Color = SnackbarDefaults.dismissActionContentColor,
) {
    val actionLabel = snackbarData.visuals.actionLabel
    val actionComposable: (@Composable () -> Unit)? = actionLabel?.optionalComposable {
        TextButton(
            colors = ButtonDefaults.textButtonColors(contentColor = actionColor),
            onClick = { snackbarData.performAction() },
            content = { Text(it, color = actionColor) }
        )
    }

    val dismissActionComposable: (@Composable () -> Unit)? =
        optionalComposable(snackbarData.visuals.withDismissAction) {
            IconButton(
                onClick = { snackbarData.dismiss() },
                content = {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = null,
                    )
                }
            )
        }

    androidx.compose.material3.Snackbar(
        modifier = modifier.padding(sizes.small),
        action = actionComposable,
        dismissAction = dismissActionComposable,
        actionOnNewLine = actionOnNewLine,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        actionContentColor = actionContentColor,
        dismissActionContentColor = dismissActionContentColor,
        content = { Text(snackbarData.visuals.message) }
    )
}

