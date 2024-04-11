package com.mafraq.presentation.features.chat.group

import com.mafraq.data.entities.chat.Message


interface ChatGroupInteractionListener {
    fun onSendMessage()
    fun onMessageChange(value: String)
    fun onDeleteMessage(messageId: String, index: Int)
    fun onEditMessage(originalMessage: Message, index: Int)

    object Preview : ChatGroupInteractionListener {
        override fun onSendMessage() = Unit
        override fun onMessageChange(value: String) = Unit
        override fun onDeleteMessage(messageId: String, index: Int) = Unit
        override fun onEditMessage(originalMessage: Message, index: Int) = Unit
    }
}
