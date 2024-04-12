package com.mafraq.presentation.features.chat.support

import com.mafraq.data.remote.models.chat.MessageRemote


interface ChatSupportInteractionListener {
    fun onNavigateBack()
    fun onSendMessage()
    fun onMessageChange(value: String)
    fun onDeleteMessage(messageId: String, index: Int)
    fun onEditMessage(originalMessageRemote: MessageRemote, index: Int)

    object Preview : ChatSupportInteractionListener {
        override fun onNavigateBack() = Unit
        override fun onSendMessage() = Unit
        override fun onMessageChange(value: String) = Unit
        override fun onDeleteMessage(messageId: String, index: Int) = Unit
        override fun onEditMessage(originalMessageRemote: MessageRemote, index: Int) = Unit
    }
}
