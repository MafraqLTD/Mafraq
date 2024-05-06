package com.mafraq.driver.features.map


import androidx.compose.runtime.Immutable
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.map.Location
import com.mafraq.data.remote.mappers.toPoint
import com.mapbox.geojson.Point


@Immutable
data class MapUiState(
    val isPermissionGranted: Boolean = false,
    val showDriverDetails: Boolean = false,
    val isViewOnly: Boolean = false,
    val currentLocation: Location = MOSUL_LOCATION,
    val destinationLocation: Location = MOSUL_LOCATION,
    val directions: List<Point> = emptyList(),
    val isLoading: Boolean = false,
    val directionsZoomLevel: Double = DIRECTIONS_ZOOM_LEVEL
) {
    val zoomLevel: Double
        get() = if (isViewOnly)
            DESTINATION_ZOOM_LEVEL
        else
            DEFAULT_ZOOM_LEVEL

    private companion object {
        const val DEFAULT_ZOOM_LEVEL = 13.0
        const val DIRECTIONS_ZOOM_LEVEL = 11.5
        const val DESTINATION_ZOOM_LEVEL = 14.0
        const val MOSUL_LATITUDE = 33.2232
        const val MOSUL_LONGITUDE = 43.6793
        val MOSUL_LOCATION = Location(MOSUL_LATITUDE, MOSUL_LONGITUDE)
    }
}
