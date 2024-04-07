package com.mafraq.presentation.features.map


import androidx.compose.runtime.Immutable
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.map.Location


@Immutable
data class MapUiState(
    val isPermissionGranted: Boolean = false,
    val showDriverDetails: Boolean = false,
    val currentLocation: Location = Location(33.2232, 43.6793),
    val selectedDriver: Driver = Driver(),
    val availableDrivers: List<Driver> = emptyList(),
)
