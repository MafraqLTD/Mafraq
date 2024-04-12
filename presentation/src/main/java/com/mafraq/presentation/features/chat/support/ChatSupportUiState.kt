package com.mafraq.presentation.features.chat.support

import com.mafraq.data.remote.models.chat.MessageRemote
import com.mafraq.presentation.utils.extensions.emptyString


data class ChatSupportUiState(
    val memberName: String = emptyString(),
    val message: String = emptyString(),
    val messageRemotes: List<MessageRemote> = emptyList(),
    val isMemberActive: Boolean = false,
)
