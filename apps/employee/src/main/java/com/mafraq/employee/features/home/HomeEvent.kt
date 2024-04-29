package com.mafraq.employee.features.home


interface HomeEvent {
    object NavigateToMap : HomeEvent

    object NavigateToSearch : HomeEvent

    object NavigateToSupportChat : HomeEvent

    object NavigateToChatGroup : HomeEvent
}
