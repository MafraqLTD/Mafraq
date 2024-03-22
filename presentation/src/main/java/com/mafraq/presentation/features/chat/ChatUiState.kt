package com.mafraq.presentation.features.chat

import com.mafraq.data.entities.chat.Message
import com.mafraq.presentation.utils.extensions.emptyString


data class ChatUiState(
    val title: String = emptyString(),
    val message: String = emptyString(),
    val messages: List<Message> = emptyList(),
    val isUserActive: Boolean = false,
)
