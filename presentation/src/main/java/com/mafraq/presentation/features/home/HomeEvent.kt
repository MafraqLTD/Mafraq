package com.mafraq.presentation.features.home


interface HomeEvent {
    data class NavigateToMap(
        val longitude: Double? = null,
        val latitude: Double? = null
    ) : HomeEvent

    object NavigateToSupportChat : HomeEvent
}
