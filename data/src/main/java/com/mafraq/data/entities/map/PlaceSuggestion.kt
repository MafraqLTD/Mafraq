package com.mafraq.data.entities.map



data class PlaceSuggestion(
    val name: String = "",
    val formattedAddress: String = ""

)

data class PlaceSuggestionWithCoordinate(
    val name: String,
    val formattedAddress: String,
    val location: Location
)
