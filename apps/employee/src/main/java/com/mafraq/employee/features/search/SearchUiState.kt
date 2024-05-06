package com.mafraq.employee.features.search

import com.mafraq.data.entities.map.PlaceSuggestion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


data class SearchUiState(
    val placesSuggestions: List<PlaceSuggestion> = emptyList(),
    val searchQuery: Flow<String> = emptyFlow(),
    val isLoading: Boolean = false
)
