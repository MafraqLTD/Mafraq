package com.mafraq.presentation.features.map


import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.map.Location


data class MapUiState(
    val isPermissionGranted: Boolean = false,
    val currentLocation: Location = Location(35.5462, 45.4294),
    val drivers: List<Driver> = listOf(
        Driver(
            name = "A",
            snippet = "Snippet A",
            location = Location(35.5462, 45.4294)
        ),
        Driver(
            name = "B",
            snippet = "Snippet B",
            location = Location(35.5472, 45.4274)
        )
    )
)
