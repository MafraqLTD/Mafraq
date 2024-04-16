package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.MapperList
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.mafraq.data.entities.map.PlaceSuggestion
import javax.inject.Inject


class PlaceSuggestionMapper @Inject constructor(

): MapperList<AutocompletePrediction, PlaceSuggestion> {
    override fun map(from: AutocompletePrediction): PlaceSuggestion = from.run {
        PlaceSuggestion(
            name = getPrimaryText(null).toString(),
            formattedAddress = getSecondaryText(null).toString(),
        )
    }

    override fun mapList(from: List<AutocompletePrediction>): List<PlaceSuggestion> =
        from.map(::map)
}
