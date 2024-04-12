package com.mafraq.presentation.features.chat.group

import android.view.MenuItem
import android.widget.PopupMenu
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.AppOutlinedTextField
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.features.chat.components.ChatGroupHeader
import com.mafraq.presentation.features.chat.components.ContextDropDown
import com.mafraq.presentation.features.chat.components.MessageItem
import com.mafraq.presentation.utils.extensions.detectTapGestures
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string


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
    val focusManager = LocalFocusManager.current
    var showConextMenu by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .detectTapGestures { focusManager.clearFocus() }
    ) {
        ChatGroupHeader(
            title = state.title,
            members = state.members,
            connectedMembers = state.connectedMembers,
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .animateContentSize(),
            contentPadding = PaddingValues(sizes.medium),
            verticalArrangement = Arrangement.spacedBy(sizes.small)
        ) {
            itemsIndexed(
                items = state.messages,
                key = { _, message -> message.id }) { index, message ->

                ContextDropDown(onItemClick = {}) {
                    MessageItem(
                        message = message,
                        showSender = true,
                        onClick = {
                            showConextMenu = true
                        }
                    )
                }


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
