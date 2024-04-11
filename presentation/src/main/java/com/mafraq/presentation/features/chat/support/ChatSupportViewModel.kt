package com.mafraq.presentation.features.chat.support

import com.mafraq.data.entities.chat.Message
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.utils.extensions.emptyString
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ChatSupportViewModel @Inject constructor(

) : BaseViewModel<ChatSupportUiState, ChatSupportEvent>(ChatSupportUiState()),
    ChatSupportInteractionListener {
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
        emitNewEvent(ChatSupportEvent.OnNavigateBack)
    }

    override fun onMessageChange(value: String) = updateState {
        copy(message = value)
    }
}
