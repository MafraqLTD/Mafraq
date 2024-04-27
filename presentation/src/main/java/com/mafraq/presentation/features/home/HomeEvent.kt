package com.mafraq.presentation.features.home


interface HomeEvent {
    object NavigateToMap : HomeEvent

    object NavigateToSearch : HomeEvent

    object NavigateToSupportChat : HomeEvent

    object NavigateToChatGroup : HomeEvent
}
