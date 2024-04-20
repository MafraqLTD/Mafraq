package com.mafraq.presentation.features.home

import com.mafraq.data.entities.home.Ad
import com.mafraq.data.entities.map.PlaceSuggestion
import com.mafraq.presentation.utils.extensions.emptyString


data class HomeUiState(
    val ads: List<Ad> = emptyList(),
    val placesSuggestions: List<PlaceSuggestion> = emptyList(),
    val searchQuery: String = emptyString(),
    val isLoading: Boolean = false
)
