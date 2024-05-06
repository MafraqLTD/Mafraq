package com.mafraq.presentation.features.chat.components

import androidx.compose.runtime.Composable
import com.google.android.play.integrity.internal.o
import com.mafraq.data.entities.chat.Message
import com.mafraq.presentation.features.chat.group.GroupChatContextMenuAction


@Composable
fun MessageItemWithContextMenu(
    message: Message,
    onEditMessage: () -> Unit,
    onDeleteMessage: () -> Unit,
    onCopyMessage: () -> Unit,
) {
    ContextMenu(
        alignment = ContextMenuAlignment.End,
        actions = GroupChatContextMenuAction.getOptions {
            if (message.isFromMe)
                true
            else
                it == GroupChatContextMenuAction.Copy
        },
        onActionClicked = { action ->
            when (action) {
                GroupChatContextMenuAction.Edit -> onEditMessage()
                GroupChatContextMenuAction.Delete -> onDeleteMessage()
                GroupChatContextMenuAction.Copy -> onCopyMessage()
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
