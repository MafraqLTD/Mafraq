package com.mafraq.presentation.features.chat.group

import androidx.annotation.StringRes
import com.mafraq.presentation.R
import com.mafraq.presentation.features.chat.components.ContextMenuAction


enum class GroupChatContextMenuAction(@StringRes override val nameResId: Int) : ContextMenuAction {
    Edit(R.string.edit),
    Delete(R.string.delete)
}
