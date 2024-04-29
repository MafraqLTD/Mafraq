package com.mafraq.employee.features.search

import com.mafraq.data.entities.map.PlaceSuggestion


interface SearchInteractionListener {
    fun navigateBack()
    fun onClearSearch()
    fun navigateToMap()
    fun onSearchQueryChange(value: String)
    fun onSelectPlace(place: PlaceSuggestion)

    object Preview : SearchInteractionListener {
        override fun navigateBack() {}
        override fun onClearSearch() {}
        override fun navigateToMap() {}
        override fun onSearchQueryChange(value: String) {}
        override fun onSelectPlace(place: PlaceSuggestion) {}
    }
}
