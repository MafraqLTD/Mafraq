package com.mafraq.driver.features.map


sealed interface MapEvent {

    data class OnNavigateToProfile(
        val latitude: Double,
        val longitude: Double
    ) : MapEvent

    data object OnNavigateBack : MapEvent
}
