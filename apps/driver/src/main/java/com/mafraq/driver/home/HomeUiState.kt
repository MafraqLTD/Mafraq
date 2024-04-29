package com.mafraq.driver.home

import com.mafraq.data.entities.home.Ad
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.map.PlaceSuggestion
import com.mafraq.presentation.utils.extensions.emptyString


data class HomeUiState(
    val ads: List<Ad> = emptyList(),
    val isSubscribed: Boolean = false,
    val pendingDriver: Driver? = null
)
