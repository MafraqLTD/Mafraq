package com.mafraq.presentation.features.chatSupport


interface ChatSupportInteractionListener {
    fun onSendMessage()
    fun onNavigateBack()
    fun onMessageChange(value: String)

    object Preview : ChatSupportInteractionListener {
        override fun onSendMessage() = Unit
        override fun onNavigateBack() = Unit
        override fun onMessageChange(value: String) = Unit
    }
}
