package com.mafraq.presentation.features.chat.support

import com.mafraq.data.entities.chat.Message
import com.mafraq.data.repository.chat.support.SupportChatRepository
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.utils.extensions.emptyString
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ChatSupportViewModel @Inject constructor(
    private val supportChatRepository: SupportChatRepository
) : BaseViewModel<ChatSupportUiState, ChatSupportEvent>(ChatSupportUiState()),
    ChatSupportInteractionListener {

    override fun onSendMessage() {
        val message = Message(
            isFromMe = true,
            content = state.value.message,
        )

        updateState {
            copy(
                message = emptyString(),
                messages = messages + message
            )
        }

        tryToExecute(
            block = { supportChatRepository.sendMessage(message) },
            onSuccess = {
                // TODO: Handle success
            },
            onError = {
                // TODO: Handle error
            }
        )
    }

    override fun onEditMessage(originalMessage: Message, index: Int) {
        val messages = state.value.messages.toMutableList()
        val message = originalMessage.copy(content = state.value.message)
        tryToExecute(
            block = { supportChatRepository.sendMessage(message) },
            onSuccess = {
                messages[index] = message
                updateState {
                    copy(
                        message = emptyString(),
                        messages = messages
                    )
                }
            },
            onError = {
                // TODO: Handle error
            }
        )
    }

    override fun onDeleteMessage(messageId: String, index: Int) {
        val messages = state.value.messages.toMutableList()
        tryToExecute(
            block = { supportChatRepository.deleteMessage(messageId) },
            onSuccess = {
                messages.removeAt(index)
                updateState { copy(messages = messages) }
            },
            onError = {
                // TODO: Handle error
            }
        )
    }

    override fun onNavigateBack() {
        emitNewEvent(ChatSupportEvent.OnNavigateBack)
    }

    override fun onMessageChange(value: String) = updateState {
        copy(message = value)
    }
}