package com.mafraq.data.entities.map

import com.mapbox.geojson.Point


data class PlaceSuggestion(
    val name: String,
    val formattedAddress: String
)

data class PlaceSuggestionWithCoordinate(
    val name: String,
    val coordinate: Point,
    val formattedAddress: String
)
