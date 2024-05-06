package com.mafraq.employee.features.map


sealed interface MapEvent {

    data class OnNavigateToProfile(
        val latitude: Double,
        val longitude: Double,
        val addressId: Int,
    ) : MapEvent

    data object OnNavigateBack : MapEvent
}
