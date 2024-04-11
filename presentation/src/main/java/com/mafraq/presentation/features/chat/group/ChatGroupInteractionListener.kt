package com.mafraq.presentation.features.chat.group


interface ChatGroupInteractionListener {
    fun onSendMessage()
    fun onNavigateBack()
    fun onMessageChange(value: String)

    object Preview : ChatGroupInteractionListener {
        override fun onSendMessage() = Unit
        override fun onNavigateBack() = Unit
        override fun onMessageChange(value: String) = Unit
    }
}
