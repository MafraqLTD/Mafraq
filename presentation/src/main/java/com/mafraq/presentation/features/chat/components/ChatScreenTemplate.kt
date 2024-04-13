package com.mafraq.presentation.features.chat.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.mafraq.data.entities.chat.Message
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.AppOutlinedTextField
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.features.chat.ChatInteractionListener
import com.mafraq.presentation.utils.extensions.detectTapGestures
import com.mafraq.presentation.utils.extensions.emptyString
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string

@Composable
fun ChatScreenTemplate(
    messageContent: String,
    messages: List<Message>,
    listener: ChatInteractionListener = ChatInteractionListener.Preview,
    header: @Composable () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    var isEditMode by remember { mutableStateOf(false) }
    var originalMessage by remember { mutableStateOf(emptyString()) }
    var onSendClick: () -> Unit by remember { mutableStateOf(listener::onSendMessage) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .detectTapGestures { focusManager.clearFocus() }
    ) {
        header()

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .animateContentSize(),
            contentPadding = PaddingValues(MafraqTheme.sizes.medium),
            verticalArrangement = Arrangement.spacedBy(MafraqTheme.sizes.small)
        ) {
            itemsIndexed(
                items = messages,
                key = { _, message -> message.id }) { index, message ->
                MessageItemWithContextMenu(
                    message = message,
                    onEditMessage = {
                        isEditMode = true
                        originalMessage = message.content
                        listener.onMessageChange(message.content)

                        onSendClick = {
                            isEditMode = false
                            originalMessage = emptyString()
                            listener.onEditMessage(
                                originalMessage = message,
                                index = index
                            )
                            onSendClick = listener::onSendMessage
                        }
                    },
                    onDeleteMessage = {
                        listener.onDeleteMessage(
                            messageId = message.id,
                            index = index
                        )
                    },
                )
            }
        }

        Column {
            EditMessageBanner(message = originalMessage, isEditMode = isEditMode) {
                isEditMode = false
                originalMessage = emptyString()
                listener.onMessageChange(emptyString())
                onSendClick = listener::onSendMessage
            }

            AppOutlinedTextField(
                value = messageContent,
                placeholder = R.string.message.string,
                onValueChange = listener::onMessageChange,
                enabledTrailingIcon = messageContent.isNotEmpty(),
                onTrailingIconClick = onSendClick,
                maxLines = 5,
                trailingIcon = R.drawable.ic_send.painter,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MafraqTheme.sizes.medium)
                    .padding(horizontal = MafraqTheme.sizes.medium)
            )
        }
    }
}