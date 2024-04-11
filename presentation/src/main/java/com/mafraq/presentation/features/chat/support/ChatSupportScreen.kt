package com.mafraq.presentation.features.chat.support

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.AppOutlinedTextField
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.features.chat.components.ChatHeader
import com.mafraq.presentation.features.chat.components.MessageItem
import com.mafraq.presentation.utils.dummyData.Dummy
import com.mafraq.presentation.utils.extensions.Listen
import com.mafraq.presentation.utils.extensions.detectTapGestures
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string


@Composable
fun ChatScreen(viewModel: ChatSupportViewModel, navController: NavController) {
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
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .detectTapGestures { focusManager.clearFocus() }
    ) {
        ChatHeader(
            title = state.title,
            isUserActive = state.isUserActive,
            onNavigateBack = listener::onNavigateBack,
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .animateContentSize(),
            contentPadding = PaddingValues(sizes.medium),
            verticalArrangement = Arrangement.spacedBy(sizes.small)
        ) {
            items(items = state.messages) { message ->
                MessageItem(
                    message = message.content,
                    receiveDate = message.receiveDate,
                    isFromMe = message.isFromMe,
                    isRead = message.isRead
                )
            }
        }

        AppOutlinedTextField(
            value = state.message,
            placeholder = R.string.message.string,
            onValueChange = listener::onMessageChange,
            enabledTrailingIcon = state.message.isNotEmpty(),
            onTrailingIconClick = listener::onSendMessage,
            maxLines = 5,
            trailingIcon = R.drawable.ic_send.painter,
            modifier = Modifier
                .fillMaxWidth()
                .padding(sizes.medium)
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun Preview() = ColumnPreview {
    Content(state = Dummy.DummyChatUiState.activeState)
}
