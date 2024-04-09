package com.mafraq.presentation.features.home

import com.mafraq.data.entities.home.Ad
import com.mafraq.presentation.utils.extensions.emptyString


data class HomeUiState(
    val ads: List<Ad> = emptyList(),
    val searchQuery: String = emptyString(),
)
