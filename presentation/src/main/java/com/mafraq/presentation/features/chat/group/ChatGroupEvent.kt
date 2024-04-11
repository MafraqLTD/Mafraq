package com.mafraq.presentation.features.chat.group


sealed interface ChatGroupEvent {
    data object OnNavigateBack : ChatGroupEvent
}
