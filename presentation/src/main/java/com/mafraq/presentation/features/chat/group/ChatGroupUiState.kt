package com.mafraq.presentation.features.chat.group

import com.mafraq.data.entities.chat.GroupChatState
import com.mafraq.data.entities.chat.Message
import com.mafraq.presentation.utils.extensions.emptyString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


data class ChatGroupUiState(
    val groupStateFlow: Flow<GroupChatState> = flow {},
    val message: String = emptyString(),
    val messages: List<Message> = emptyList(),
    val isUserActive: Boolean = false,
)
