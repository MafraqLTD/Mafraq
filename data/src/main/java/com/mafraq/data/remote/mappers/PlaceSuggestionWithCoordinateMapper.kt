package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.mafraq.data.entities.map.PlaceSuggestionWithCoordinate
import com.mapbox.search.autocomplete.PlaceAutocompleteResult
import javax.inject.Inject


class PlaceSuggestionWithCoordinateMapper @Inject constructor(

): Mapper<PlaceAutocompleteResult, PlaceSuggestionWithCoordinate> {
    override fun map(from: PlaceAutocompleteResult): PlaceSuggestionWithCoordinate = from.run {
        PlaceSuggestionWithCoordinate(
            name = name,
            coordinate = coordinate,
            formattedAddress = address?.formattedAddress.orEmpty(),
        )
    }

}
