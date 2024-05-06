package com.mafraq.data.repository.map

import com.mafraq.data.entities.map.Directions
import com.mafraq.data.entities.map.Location
import com.mafraq.data.entities.map.PlaceSuggestion
import com.mafraq.data.entities.map.PlaceSuggestionWithCoordinate
import com.mafraq.data.remote.dataSource.map.PlacesDataSource
import javax.inject.Inject


class MapPlacesRepositoryImpl @Inject constructor(
    private val placesDataSource: PlacesDataSource
) : MapPlacesRepository {

    override suspend fun getPlaceSuggestions(query: String): List<PlaceSuggestion> =
        placesDataSource.getPlaceSuggestions(query)

    override suspend fun selectSuggestedPlace(
        placeSuggestion: PlaceSuggestion
    ): PlaceSuggestionWithCoordinate = placesDataSource.selectSuggestedPlace(placeSuggestion)

    override suspend fun getDirections(
        originLocation: Location,
        destinationLocation: Location
    ): Directions = placesDataSource.getDirections(
        originLocation = originLocation,
        destinationLocation = destinationLocation
    )

    override suspend fun getLocationInfo(latitude: Double, longitude: Double): Location =
        placesDataSource.getLocationInfo(latitude, longitude)
}
