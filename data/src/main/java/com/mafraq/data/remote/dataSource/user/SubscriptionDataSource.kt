package com.mafraq.data.remote.dataSource.user

import com.mafraq.data.entities.Subscriber
import kotlinx.coroutines.flow.Flow


interface SubscriptionDataSource {
    val subscriptionsFlow : Flow<List<Subscriber>>
    val pendingSubscriptionsFlow : Flow<List<Subscriber>>
    suspend fun request(subscriber: Subscriber)
    suspend fun cancel(subscriber: Subscriber)
}
