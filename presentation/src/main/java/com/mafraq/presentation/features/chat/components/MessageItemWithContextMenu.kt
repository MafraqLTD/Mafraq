package com.mafraq.presentation.features.chat.components

import androidx.compose.runtime.Composable
import com.mafraq.data.entities.chat.Message
import com.mafraq.presentation.features.chat.group.GroupChatContextMenuAction


@Composable
fun MessageItemWithContextMenu(
    message: Message,
    onEditMessage: () -> Unit,
    onDeleteMessage: () -> Unit,
) {
    ContextMenu(
        alignment = ContextMenuAlignment.End,
        actions = GroupChatContextMenuAction.entries.toTypedArray(),
        onActionClicked = { action ->
            when (action) {
                GroupChatContextMenuAction.Edit -> onEditMessage()
                GroupChatContextMenuAction.Delete -> onDeleteMessage()
            }
        },
    ) { onClick ->
        MessageItem(
            message = message,
            showSender = true,
            onClick = onClick
        )
    }
}
