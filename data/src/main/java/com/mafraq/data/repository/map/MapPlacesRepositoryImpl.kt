package com.mafraq.data.repository.map

import com.mafraq.data.entities.map.PlaceSuggestion
import com.mafraq.data.entities.map.PlaceSuggestionWithCoordinate
import com.mafraq.data.remote.dataSource.map.GooglePlacesDataSource
import javax.inject.Inject


class MapPlacesRepositoryImpl @Inject constructor(
    private val googlePlacesDataSource: GooglePlacesDataSource
) : MapPlacesRepository {

    override suspend fun getPlaceSuggestions(query: String): List<PlaceSuggestion> =
        googlePlacesDataSource.getPlaceSuggestions(query)

    override suspend fun selectSuggestedPlace(
        placeSuggestion: PlaceSuggestion
    ): PlaceSuggestionWithCoordinate = googlePlacesDataSource.selectSuggestedPlace(placeSuggestion)
}
