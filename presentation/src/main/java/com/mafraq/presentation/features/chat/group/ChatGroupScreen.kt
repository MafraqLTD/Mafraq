package com.mafraq.presentation.features.chat.group

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mafraq.data.entities.chat.GroupChatState
import com.mafraq.presentation.features.chat.components.ChatGroupHeader
import com.mafraq.presentation.features.chat.components.ChatScreenTemplate


@Composable
fun ChatGroupScreen(viewModel: ChatGroupViewModel) {
    val state: ChatGroupUiState by viewModel.state.collectAsStateWithLifecycle()
    val listener: ChatGroupInteractionListener = viewModel

    Content(
        state = state,
        listener = listener
    )
}

@Composable
private fun Content(
    state: ChatGroupUiState = ChatGroupUiState(),
    listener: ChatGroupInteractionListener = ChatGroupInteractionListener.Preview
) {
    val groupState by state.groupStateFlow.collectAsStateWithLifecycle(initialValue = GroupChatState())
    ChatScreenTemplate(
        messageContent = state.message,
        messages = state.messages,
        listener = listener,
        header = {
            ChatGroupHeader(
                title = groupState.title,
                members = groupState.members,
                activeMembers = groupState.activeMembers,
            )
        }
    )
}
