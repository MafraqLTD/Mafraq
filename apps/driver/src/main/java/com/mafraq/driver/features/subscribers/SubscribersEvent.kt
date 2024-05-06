package com.mafraq.driver.features.subscribers


interface SubscribersEvent {
    object NavigateToMap : SubscribersEvent

    object NavigateToChatGroup : SubscribersEvent
}
