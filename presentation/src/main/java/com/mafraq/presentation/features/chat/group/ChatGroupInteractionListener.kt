package com.mafraq.presentation.features.chat.group

import com.mafraq.presentation.features.chat.ChatInteractionListener


interface ChatGroupInteractionListener : ChatInteractionListener {
    object Preview : ChatGroupInteractionListener,
        ChatInteractionListener by ChatInteractionListener.Preview
}
