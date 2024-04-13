package com.mafraq.presentation.features.chat.group

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mafraq.presentation.features.chat.components.ChatGroupHeader
import com.mafraq.presentation.features.chat.components.ChatScreenTemplate


@Composable
fun ChatGroupScreen(viewModel: ChatGroupViewModel, navController: NavController) {
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
    ChatScreenTemplate(
        messageContent = state.message,
        messages = state.messages,
        listener = listener,
        header = {
            ChatGroupHeader(
                title = state.title,
                members = state.members,
                connectedMembers = state.connectedMembers,
            )
        }
    )
}
