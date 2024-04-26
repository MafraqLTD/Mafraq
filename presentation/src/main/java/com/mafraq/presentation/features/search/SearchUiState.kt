package com.mafraq.presentation.features.search

import com.mafraq.data.entities.map.PlaceSuggestion
import com.mafraq.presentation.utils.extensions.emptyString


data class SearchUiState(
    val placesSuggestions: List<PlaceSuggestion> = emptyList(),
    val searchQuery: String = emptyString(),
    val isLoading: Boolean = false
)
