package com.mafraq.presentation.features.chat.group

import com.mafraq.data.entities.chat.Message
import com.mafraq.presentation.utils.extensions.emptyString


data class ChatGroupUiState(
    val title: String = emptyString(),
    val message: String = emptyString(),
    val messages: List<Message> = emptyList(),
    val isUserActive: Boolean = false,
    val members: Int = 0,
    val connectedMembers: Int = 0
)
