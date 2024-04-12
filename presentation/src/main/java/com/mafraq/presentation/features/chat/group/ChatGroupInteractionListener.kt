package com.mafraq.presentation.features.chat.group

import com.mafraq.data.remote.models.chat.MessageRemote


interface ChatGroupInteractionListener {
    fun onSendMessage()
    fun onMessageChange(value: String)
    fun onDeleteMessage(messageId: String, index: Int)
    fun onEditMessage(originalMessageRemote: MessageRemote, index: Int)

    object Preview : ChatGroupInteractionListener {
        override fun onSendMessage() = Unit
        override fun onMessageChange(value: String) = Unit
        override fun onDeleteMessage(messageId: String, index: Int) = Unit
        override fun onEditMessage(originalMessageRemote: MessageRemote, index: Int) = Unit
    }
}
