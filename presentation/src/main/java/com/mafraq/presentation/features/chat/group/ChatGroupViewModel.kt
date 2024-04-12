package com.mafraq.presentation.features.chat.group

import com.mafraq.data.remote.models.chat.MessageRemote
import com.mafraq.data.repository.chat.group.GroupChatRepository
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.utils.extensions.emptyString
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ChatGroupViewModel @Inject constructor(
    private val groupChatRepository: GroupChatRepository
) : BaseViewModel<ChatGroupUiState, ChatGroupEvent>(ChatGroupUiState()),
    ChatGroupInteractionListener {
    override fun onSendMessage() {
        val messageRemote = MessageRemote(
            content = state.value.message,
        )

        updateState {
            copy(
                message = emptyString(),
                messageRemotes = messageRemotes + messageRemote
            )
        }

        tryToExecute(
            block = { groupChatRepository.sendMessage(messageRemote) },
            onSuccess = {
                // TODO: Handle success
            },
            onError = {
                // TODO: Handle error
            }
        )
    }

    override fun onEditMessage(originalMessageRemote: MessageRemote, index: Int) {
        val messages = state.value.messageRemotes.toMutableList()
        val message = originalMessageRemote.copy(content = state.value.message)
        tryToExecute(
            block = { groupChatRepository.sendMessage(message) },
            onSuccess = {
                messages[index] = message
                updateState {
                    copy(
                        message = emptyString(),
                        messageRemotes = messages
                    )
                }
            },
            onError = {
                // TODO: Handle error
            }
        )
    }

    override fun onDeleteMessage(messageId: String, index: Int) {
        val messages = state.value.messageRemotes.toMutableList()
        tryToExecute(
            block = { groupChatRepository.deleteMessage(messageId) },
            onSuccess = {
                messages.removeAt(index)
                updateState { copy(messageRemotes = messages) }
            },
            onError = {
                // TODO: Handle error
            }
        )
    }

    override fun onMessageChange(value: String) = updateState {
        copy(message = value)
    }
}
