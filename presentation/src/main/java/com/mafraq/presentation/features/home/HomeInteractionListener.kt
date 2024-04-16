package com.mafraq.presentation.features.home

import com.mafraq.data.entities.map.PlaceSuggestion


interface HomeInteractionListener {
    fun onClearSearch()
    fun navigateToMap()
    fun navigateToSupportChat()
    fun onSearchQueryChange(value: String)
    fun onSelectPlace(place: PlaceSuggestion)

    object Preview : HomeInteractionListener{
        override fun onClearSearch() {}
        override fun navigateToMap() {}
        override fun navigateToSupportChat() {}
        override fun onSearchQueryChange(value: String) {}
        override fun onSelectPlace(place: PlaceSuggestion) {}
    }
}
