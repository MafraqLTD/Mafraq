package com.mafraq.presentation.features.chat.support

import com.mafraq.data.entities.chat.Message
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
        val message = Message(
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
                updateState { copy(messages = it) }
            }
        )
    }
}
