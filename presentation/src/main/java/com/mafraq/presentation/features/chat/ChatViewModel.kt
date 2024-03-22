package com.mafraq.presentation.features.chat

import com.mafraq.data.entities.chat.Message
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.utils.extensions.emptyString
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor(

) : BaseViewModel<ChatUiState, ChatEvent>(ChatUiState()), ChatInteractionListener {
    override fun onSendMessage() {
        val message = Message(
            isFromMe = true,
            isRead = false,
            content = state.value.message,
            receiveDate = "12:11 AM"
        )

        updateState {
            copy(
                message = emptyString(),
                messages = messages + message
            )
        }
    }

    override fun onNavigateBack() {
        emitNewEvent(ChatEvent.OnNavigateBack)
    }

    override fun onMessageChange(value: String) = updateState {
        copy(message = value)
    }
}
