package com.mafraq.presentation.features.chat.support

import com.mafraq.data.remote.models.chat.MessageRemote
import com.mafraq.data.repository.chat.support.SupportChatRepository
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.utils.extensions.emptyString
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class ChatSupportViewModel @Inject constructor(
    private val supportChatRepository: SupportChatRepository
) : BaseViewModel<ChatSupportUiState, ChatSupportEvent>(ChatSupportUiState()),
    ChatSupportInteractionListener {

    init {
        initialization()
    }

    override fun onSendMessage() {
        val messageRemote = MessageRemote(
            fromMe = true,
            content = state.value.message,
        )

        updateState {
            copy(
                message = emptyString(),
                messageRemotes = messageRemotes + messageRemote
            )
        }

        tryToExecute(
            block = { supportChatRepository.sendMessage(messageRemote) },
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
            block = { supportChatRepository.sendMessage(message) },
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
            block = { supportChatRepository.deleteMessage(messageId) },
            onSuccess = {
                messages.removeAt(index)
                updateState { copy(messageRemotes = messages) }
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

    private fun initialization() {
        tryToCollect(
            block = { supportChatRepository.chatMemberStateFlow },
            onNewValue = {
                Timber.d("ChatMemberStateFlow: $it")
                updateState {
                    copy(
                        memberName = it.name,
                        isMemberActive = it.isActive,
                    )
                }
            }
        )

        tryToCollect(
            block = { supportChatRepository.chatFlow },
            onNewValue = {
                Timber.d("Messages: $it")
                updateState { copy(messageRemotes = it) }
            }
        )
    }
}
