package com.mafraq.presentation.features.chat.group

import com.mafraq.data.remote.models.chat.MessageRemote
import com.mafraq.presentation.utils.extensions.emptyString


data class ChatGroupUiState(
    val title: String = emptyString(),
    val message: String = emptyString(),
    val messageRemotes: List<MessageRemote> = emptyList(),
    val isUserActive: Boolean = false,
    val members: Int = 0,
    val connectedMembers: Int = 0
)
