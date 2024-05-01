package com.mafraq.driver.home

import com.mafraq.data.entities.Subscriber
import com.mafraq.data.entities.home.Ad
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.map.PlaceSuggestion
import com.mafraq.presentation.utils.extensions.emptyString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


data class HomeUiState(
    val ads: List<Ad> = emptyList(),
    val isSubscribed: Boolean = false,
    val pendingFlow: Flow<List<Subscriber>> = flow {}
)
