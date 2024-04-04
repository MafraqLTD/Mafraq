package com.mafraq.presentation.features.map


import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.map.Location


data class MapUiState(
    val isPermissionGranted: Boolean = false,
    val currentLocation: Location = Location(33.2232, 43.6793),
    val drivers: List<Driver> = emptyList()
)
