package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.MapperList
import com.mafraq.data.entities.map.PlaceSuggestion
import com.mapbox.search.autocomplete.PlaceAutocompleteSuggestion
import javax.inject.Inject


class PlaceSuggestionMapper @Inject constructor(

): MapperList<PlaceAutocompleteSuggestion, PlaceSuggestion> {
    override fun map(from: PlaceAutocompleteSuggestion): PlaceSuggestion = from.run {
        PlaceSuggestion(
            name = name,
            formattedAddress = formattedAddress.orEmpty(),
        )
    }

    override fun mapList(from: List<PlaceAutocompleteSuggestion>): List<PlaceSuggestion> =
        from.map(::map)
}
