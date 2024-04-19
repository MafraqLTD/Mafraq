package com.mafraq.presentation.features.map


import androidx.compose.runtime.Immutable
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.map.Location


@Immutable
data class MapUiState(
    val isPermissionGranted: Boolean = false,
    val showDriverDetails: Boolean = false,
    val isDestination: Boolean = false,
    val currentLocation: Location = MOSUL_LOCATION,
    val destinationLocation: Location = MOSUL_LOCATION,
    val selectedDriver: Driver = Driver(),
    val availableDrivers: List<Driver> = emptyList(),
) {
    val zoomLevel: Double
        get() = if (isDestination) DESTINATION_ZOOM_LEVEL else DEFAULT_ZOOM_LEVEL

    val cameraLocation: Location
        get() = if (isDestination) destinationLocation else currentLocation

    private companion object {
        const val DEFAULT_ZOOM_LEVEL = 13.0
        const val DESTINATION_ZOOM_LEVEL = 14.0
        const val MOSUL_LATITUDE = 33.2232
        const val MOSUL_LONGITUDE = 43.6793
        val MOSUL_LOCATION = Location(MOSUL_LATITUDE, MOSUL_LONGITUDE)
    }
}
