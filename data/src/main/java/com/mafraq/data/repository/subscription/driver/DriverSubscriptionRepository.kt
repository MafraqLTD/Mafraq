package com.mafraq.data.repository.subscription.driver

import com.mafraq.data.entities.Subscriber
import kotlinx.coroutines.flow.Flow


interface DriverSubscriptionRepository {
    val subscribersFlow : Flow<List<Subscriber>>
    val pendingFlow : Flow<List<Subscriber>>
    fun select(subscriber: Subscriber?)
    suspend fun cancel(subscriber: Subscriber)
    suspend fun unsubscribe(subscriber: Subscriber)
    suspend fun accept()
}
