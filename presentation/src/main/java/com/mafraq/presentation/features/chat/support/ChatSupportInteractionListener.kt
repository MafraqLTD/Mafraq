package com.mafraq.presentation.features.chat.support

import com.mafraq.presentation.features.chat.ChatInteractionListener


interface ChatSupportInteractionListener : ChatInteractionListener {
    fun onNavigateBack()

    object Preview : ChatSupportInteractionListener,
        ChatInteractionListener by ChatInteractionListener.Preview {
        override fun onNavigateBack() = Unit
    }
}
