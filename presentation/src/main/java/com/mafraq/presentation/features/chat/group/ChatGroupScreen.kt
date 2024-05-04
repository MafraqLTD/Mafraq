package com.mafraq.presentation.features.chat.group

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mafraq.data.entities.chat.GroupChatState
import com.mafraq.presentation.design.components.navigation.LocalAppUserType
import com.mafraq.presentation.features.chat.components.ChatGroupHeader
import com.mafraq.presentation.features.chat.components.ChatScreenTemplate
import com.mafraq.presentation.utils.extensions.Listen


@Composable
fun ChatGroupScreen(viewModel: ChatGroupViewModel, navController: NavController) {
    val state: ChatGroupUiState by viewModel.state.collectAsStateWithLifecycle()
    val event: ChatGroupEvent? by viewModel.event.collectAsState(null)
    val listener: ChatGroupInteractionListener = viewModel

    Content(
        state = state,
        listener = listener
    )

    event?.Listen { currentEvent ->
        when(currentEvent) {
            ChatGroupEvent.OnNavigateBack -> navController.navigateUp()
        }
    }
}

@Composable
private fun Content(
    state: ChatGroupUiState = ChatGroupUiState(),
    listener: ChatGroupInteractionListener = ChatGroupInteractionListener.Preview
) {
    val messages by state.chatFlow.collectAsStateWithLifecycle(initialValue = emptyList())
    val groupState by state.groupStateFlow.collectAsStateWithLifecycle(initialValue = GroupChatState())
    ChatScreenTemplate(
        messageContent = state.message,
        messages = messages,
        listener = listener,
        header = {
            ChatGroupHeader(
                title = groupState.title,
                members = groupState.members,
                activeMembers = groupState.activeMembers,
                showBackButton = LocalAppUserType.current.isDriverApp,
                onNavigateBack = listener::onNavigateBack
            )
        }
    )
}
