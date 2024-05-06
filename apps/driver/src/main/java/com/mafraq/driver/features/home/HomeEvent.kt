package com.mafraq.driver.features.home


interface HomeEvent {
    object NavigateToMap : HomeEvent

    object NavigateToSupportChat : HomeEvent

    object NavigateToChatGroup : HomeEvent
}
