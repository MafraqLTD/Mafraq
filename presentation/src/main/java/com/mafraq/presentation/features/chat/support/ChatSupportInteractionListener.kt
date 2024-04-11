package com.mafraq.presentation.features.chat.support

import com.mafraq.data.entities.chat.Message


interface ChatSupportInteractionListener {
    fun onNavigateBack()
    fun onSendMessage()
    fun onMessageChange(value: String)
    fun onDeleteMessage(messageId: String, index: Int)
    fun onEditMessage(originalMessage: Message, index: Int)

    object Preview : ChatSupportInteractionListener {
        override fun onNavigateBack() = Unit
        override fun onSendMessage() = Unit
        override fun onMessageChange(value: String) = Unit
        override fun onDeleteMessage(messageId: String, index: Int) = Unit
        override fun onEditMessage(originalMessage: Message, index: Int) = Unit
    }
}
