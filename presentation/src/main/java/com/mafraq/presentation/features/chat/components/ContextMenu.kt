package com.mafraq.presentation.features.chat.components

import androidx.annotation.StringRes
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import com.mafraq.presentation.utils.extensions.string


@Stable
interface ContextMenuAction {
    @get:StringRes
    val nameResId: Int
}

enum class ContextMenuAlignment {
    Start,
    End
}

@Composable
fun ContextMenu(
    vararg actions: ContextMenuAction,
    alignment: ContextMenuAlignment = ContextMenuAlignment.Start,
    onActionClicked: (action: ContextMenuAction) -> Unit,
    content: @Composable (changeVisibilityState: () -> Unit) -> Unit
) {
    val density = LocalDensity.current
    var endOffset by remember { mutableStateOf(DpOffset.Zero) }
    var showContextMenu by remember { mutableStateOf(false) }
    val changeVisibilityState = { showContextMenu = !showContextMenu }
    val screenWidth = with(density) { LocalConfiguration.current.screenWidthDp.toDp() }
    val offset = when (alignment) {
        ContextMenuAlignment.Start -> DpOffset.Zero
        ContextMenuAlignment.End -> endOffset
    }

    DropdownMenu(
        expanded = showContextMenu,
        onDismissRequest = changeVisibilityState,
        offset = offset,
        modifier = Modifier.onGloballyPositioned {
            with(density) {
                val menuWidth = it.size.width.toDp()
                val menuHeight = it.size.height.toDp()

                endOffset = DpOffset(
                    x = screenWidth + menuWidth,
                    y = menuHeight + (menuHeight / 1.25f)
                )
            }
        }
    ) {
        actions.forEach { action ->
            DropdownMenuItem(
                text = { Text(text = action.nameResId.string) },
                onClick = {
                    onActionClicked(action)
                    changeVisibilityState()
                }
            )
        }
    }

    content {
        changeVisibilityState()
    }
}
