package com.mafraq.presentation.features.chat.group

import com.mafraq.data.entities.chat.Message
import com.mafraq.data.repository.chat.group.GroupChatRepository
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.utils.extensions.emptyString
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ChatGroupViewModel @Inject constructor(
    private val groupChatRepository: GroupChatRepository,
) : BaseViewModel<ChatGroupUiState, ChatGroupEvent>(
    initState = ChatGroupUiState(
        chatFlow = groupChatRepository.chatFlow,
        groupStateFlow = groupChatRepository.stateFlow,
    )
), ChatGroupInteractionListener {

    override fun onNavigateBack() {
        emitNewEvent(ChatGroupEvent.OnNavigateBack)
    }

    override fun onSendMessage() {
        val message = Message(content = state.value.message,)

        clearMessageField()

        tryToExecute(
            block = { groupChatRepository.sendMessage(message) },
            onSuccess = {
                // TODO: Handle success
            },
            onError = {
                // TODO: Handle error
            }
        )
    }

    override fun onEditMessage(originalMessage: Message, index: Int) {
        val message = originalMessage.copy(content = state.value.message)
        tryToExecute(
            block = { groupChatRepository.sendMessage(message) },
            onSuccess = { clearMessageField() },
            onError = {
                // TODO: Handle error
            }
        )
    }

    override fun onDeleteMessage(messageId: String, index: Int) {
        tryToExecute(
            block = { groupChatRepository.deleteMessage(messageId) },
            onSuccess = {
                // TODO: Handle success
            },
            onError = {
                // TODO: Handle error
            }
        )
    }

    override fun onMessageChange(value: String) = updateState {
        copy(message = value)
    }

    private fun clearMessageField() = updateState { copy(message = emptyString()) }
}
