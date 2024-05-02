package com.mafraq.driver.features.subscribers

import com.mafraq.data.entities.Subscriber
import com.mafraq.data.entities.home.Ad
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


data class SubscribersUiState(
    val ads: List<Ad> = emptyList(),
    val isSubscribed: Boolean = false,
    val pendingFlow: Flow<List<Subscriber>> = flow {}
)
