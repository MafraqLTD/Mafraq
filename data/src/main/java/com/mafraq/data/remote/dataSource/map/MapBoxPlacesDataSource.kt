package com.mafraq.data.remote.dataSource.map

import com.mafraq.data.entities.map.PlaceSuggestion
import com.mafraq.data.entities.map.PlaceSuggestionWithCoordinate

interface MapBoxPlacesDataSource {

    suspend fun getPlaceSuggestions(query: String): List<PlaceSuggestion>
    suspend fun selectSuggestedPlace(placeSuggestion: PlaceSuggestion): PlaceSuggestionWithCoordinate
}
