package com.mafraq.data.remote.dataSource.map

import com.mafraq.data.entities.map.PlaceSuggestion
import com.mafraq.data.entities.map.PlaceSuggestionWithCoordinate
import com.mafraq.data.remote.mappers.PlaceSuggestionMapper
import com.mafraq.data.remote.mappers.PlaceSuggestionWithCoordinateMapper
import com.mapbox.geojson.BoundingBox
import com.mapbox.geojson.Point
import com.mapbox.search.autocomplete.PlaceAutocomplete
import com.mapbox.search.autocomplete.PlaceAutocompleteOptions
import com.mapbox.search.autocomplete.PlaceAutocompleteSuggestion
import com.mapbox.search.autocomplete.PlaceAutocompleteType
import com.mapbox.search.common.IsoCountryCode
import com.mapbox.search.common.IsoLanguageCode
import timber.log.Timber
import javax.inject.Inject


class MapBoxPlacesDataSourceImpl @Inject constructor(
    private val placeAutocomplete: PlaceAutocomplete,
    private val placeSuggestionMapper: PlaceSuggestionMapper,
    private val placeSuggestionWithCoordinateMapper: PlaceSuggestionWithCoordinateMapper,
): MapBoxPlacesDataSource {
    private val originalSuggestions: MutableList<PlaceAutocompleteSuggestion> = mutableListOf()

    override suspend fun getPlaceSuggestions(query: String): List<PlaceSuggestion> {
        return placeAutocomplete.suggestions(
            query = query,
            region = iraqBoundingBox,
            proximity = mosulProximityPoint,
            options = PlaceAutocompleteOptions(
                countries = listOf(IsoCountryCode.IRAQ),
                language = IsoLanguageCode.ARABIC,
                types = listOf(PlaceAutocompleteType.AdministrativeUnit.Place)
            )
        ).also {
            originalSuggestions.clear()
            originalSuggestions.addAll(it.value ?: emptyList())
        }
            .mapValue(placeSuggestionMapper::mapList)
            .value ?: emptyList()
    }

    override suspend fun selectSuggestedPlace(placeSuggestion: PlaceSuggestion): PlaceSuggestionWithCoordinate {
        val place = originalSuggestions.first { it.name == placeSuggestion.name }
        val placeWithCoordinate = placeAutocomplete.select(place).value ?: error("Place not found")
        Timber.d(placeWithCoordinate.toString())
        return placeSuggestionWithCoordinateMapper.map(placeWithCoordinate)
    }

    companion object {
        val mosulProximityPoint: Point = Point.fromLngLat(
            /* longitude = */ 43.127,
            /* latitude = */ 36.341
        )
        private val iraqBoundingBox: BoundingBox = BoundingBox.fromLngLats(
            /* west = */ 38.7944,
            /* south = */ 29.0611,
            /* east = */ 48.5558,
            /* north = */ 37.3736
        )
    }
}
