package com.mafraq.driver.home


interface HomeEvent {
    object NavigateToMap : HomeEvent

    object NavigateToSupportChat : HomeEvent

    object NavigateToChatGroup : HomeEvent
}
