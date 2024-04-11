package com.mafraq.presentation.features.chat.support


sealed interface ChatSupportEvent {
    data object OnNavigateBack : ChatSupportEvent
}
