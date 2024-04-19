package com.mafraq.data.repository.map

import com.mafraq.data.entities.map.Location
import com.mafraq.data.entities.map.PlaceSuggestion
import com.mafraq.data.entities.map.PlaceSuggestionWithCoordinate

interface MapPlacesRepository {

    suspend fun getPlaceSuggestions(query: String): List<PlaceSuggestion>
    suspend fun selectSuggestedPlace(placeSuggestion: PlaceSuggestion): PlaceSuggestionWithCoordinate
    suspend fun getDirections(originLocation: Location, destinationLocation: Location): List<Location>
}
