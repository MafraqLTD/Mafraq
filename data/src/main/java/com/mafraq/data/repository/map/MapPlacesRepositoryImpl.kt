package com.mafraq.data.repository.map

import com.mafraq.data.entities.map.PlaceSuggestion
import com.mafraq.data.entities.map.PlaceSuggestionWithCoordinate
import com.mafraq.data.remote.dataSource.map.MapBoxPlacesDataSource
import javax.inject.Inject


class MapPlacesRepositoryImpl @Inject constructor(
    private val mapBoxPlacesDataSource: MapBoxPlacesDataSource
) : MapPlacesRepository {

    override suspend fun getPlaceSuggestions(query: String): List<PlaceSuggestion> =
        mapBoxPlacesDataSource.getPlaceSuggestions(query)

    override suspend fun selectSuggestedPlace(
        placeSuggestion: PlaceSuggestion
    ): PlaceSuggestionWithCoordinate = mapBoxPlacesDataSource.selectSuggestedPlace(placeSuggestion)
}
