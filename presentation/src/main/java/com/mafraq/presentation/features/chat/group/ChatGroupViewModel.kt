package com.mafraq.presentation.features.chat.group

import com.mafraq.data.entities.chat.Message
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.utils.extensions.emptyString
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ChatGroupViewModel @Inject constructor(

) : BaseViewModel<ChatGroupUiState, ChatGroupEvent>(ChatGroupUiState()),
    ChatGroupInteractionListener {
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
        emitNewEvent(ChatGroupEvent.OnNavigateBack)
    }

    override fun onMessageChange(value: String) = updateState {
        copy(message = value)
    }
}
