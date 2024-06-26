package com.mafraq.data.remote.dataSource.subscription.driver

import com.mafraq.data.entities.Subscriber
import kotlinx.coroutines.flow.Flow


interface DriverSubscriptionDataSource {
    val allMembersFlow : Flow<List<Subscriber>>
    val subscribersFlow : Flow<List<Subscriber>>
    val pendingFlow : Flow<List<Subscriber>>
    suspend fun unsubscribe(subscriber: Subscriber)
    suspend fun cancel(subscriber: Subscriber)
    suspend fun accept(subscriber: Subscriber)
}
