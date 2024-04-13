package com.mafraq.presentation.features.chat.support

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mafraq.presentation.features.chat.components.ChatScreenTemplate
import com.mafraq.presentation.features.chat.components.ChatSupportHeader
import com.mafraq.presentation.utils.extensions.Listen


@Composable
fun ChatSupportScreen(viewModel: ChatSupportViewModel, navController: NavController) {
    val state: ChatSupportUiState by viewModel.state.collectAsStateWithLifecycle()
    val event: ChatSupportEvent? by viewModel.event.collectAsState(null)
    val listener: ChatSupportInteractionListener = viewModel

    Content(
        state = state,
        listener = listener
    )

    event?.Listen { currentEvent ->
        when (currentEvent) {
            ChatSupportEvent.OnNavigateBack -> navController.navigateUp()
        }
    }
}


@Composable
private fun Content(
    state: ChatSupportUiState = ChatSupportUiState(),
    listener: ChatSupportInteractionListener = ChatSupportInteractionListener.Preview
) {
    ChatScreenTemplate(
        messageContent = state.message,
        messages = state.messages,
        listener = listener,
        header = {
            ChatSupportHeader(
                title = state.memberName,
                isMemberActive = state.isMemberActive,
                onNavigateBack = listener::onNavigateBack,
            )
        }
    )
}
