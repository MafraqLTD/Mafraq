package com.mafraq.data.repository.subscription.driver

import com.mafraq.data.entities.Subscriber
import kotlinx.coroutines.flow.Flow


interface DriverSubscriptionRepository {
    val membersFlow : Flow<List<Subscriber>>
    val pendingFlow : Flow<List<Subscriber>>
    suspend fun cancel(subscriber: Subscriber)
    suspend fun accept(subscriber: Subscriber)
}