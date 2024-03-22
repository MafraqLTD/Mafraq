package com.mafraq.presentation.features.map


import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.map.Location


data class MapUiState(
    val currentLocation: Location = Location(),
    val drivers: List<Driver> = emptyList()
)
