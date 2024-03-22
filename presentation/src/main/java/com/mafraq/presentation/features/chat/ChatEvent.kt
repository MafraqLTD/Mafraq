package com.mafraq.presentation.features.chat


sealed interface ChatEvent {
    data object OnNavigateBack : ChatEvent
}
