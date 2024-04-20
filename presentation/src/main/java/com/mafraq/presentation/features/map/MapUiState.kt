package com.mafraq.presentation.features.map


import androidx.compose.runtime.Immutable
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.map.Location
import com.mafraq.data.remote.mappers.toPoint
import com.mapbox.geojson.Point


@Immutable
data class MapUiState(
    val isPermissionGranted: Boolean = false,
    val showDriverDetails: Boolean = false,
    val isDestination: Boolean = false,
    val originLocation: Location = MOSUL_LOCATION,
    val destinationLocation: Location = MOSUL_LOCATION,
    val directions: List<Point> = emptyList(),
    val selectedDriver: Driver = Driver(),
    val availableDrivers: List<Driver> = emptyList(),
    val isLoading: Boolean = false,
    val isFromProfileForHomeAddress: Boolean = false,
    val isFromProfileForWorkAddress: Boolean = false,
    val directionsZoomLevel: Double = DIRECTIONS_ZOOM_LEVEL
) {
    val zoomLevel: Double
        get() = if (isDestination && directions.isNotEmpty())
            // directionsZoomLevel
            DIRECTIONS_ZOOM_LEVEL
        else if (isDestination)
            DESTINATION_ZOOM_LEVEL
        else
            DEFAULT_ZOOM_LEVEL

    val currentLocation: Point
        get() = (if (isDestination) destinationLocation else originLocation).toPoint()

    private companion object {
        const val DEFAULT_ZOOM_LEVEL = 13.0
        const val DIRECTIONS_ZOOM_LEVEL = 11.5
        const val DESTINATION_ZOOM_LEVEL = 14.0
        const val MOSUL_LATITUDE = 33.2232
        const val MOSUL_LONGITUDE = 43.6793
        val MOSUL_LOCATION = Location(MOSUL_LATITUDE, MOSUL_LONGITUDE)
    }
}
