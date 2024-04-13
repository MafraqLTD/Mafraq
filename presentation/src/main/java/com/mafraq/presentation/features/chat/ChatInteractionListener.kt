package com.mafraq.presentation.features.chat

import com.mafraq.data.entities.chat.Message


interface ChatInteractionListener {
    fun onSendMessage()
    fun onMessageChange(value: String)
    fun onDeleteMessage(messageId: String, index: Int)
    fun onEditMessage(originalMessage: Message, index: Int)

    object Preview : ChatInteractionListener {
        override fun onSendMessage() = Unit
        override fun onMessageChange(value: String) = Unit
        override fun onDeleteMessage(messageId: String, index: Int) = Unit
        override fun onEditMessage(originalMessage: Message, index: Int) = Unit
    }
}
