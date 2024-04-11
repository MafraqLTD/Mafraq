package com.mafraq.presentation.features.chatSupport


sealed interface ChatSupportEvent {
    data object OnNavigateBack : ChatSupportEvent
}
