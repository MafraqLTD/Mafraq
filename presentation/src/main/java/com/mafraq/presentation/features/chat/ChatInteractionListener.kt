package com.mafraq.presentation.features.chat


interface ChatInteractionListener {
    fun onSendMessage()
    fun onNavigateBack()
    fun onMessageChange(value: String)

    object Preview : ChatInteractionListener {
        override fun onSendMessage() = Unit
        override fun onNavigateBack() = Unit
        override fun onMessageChange(value: String) = Unit
    }
}
