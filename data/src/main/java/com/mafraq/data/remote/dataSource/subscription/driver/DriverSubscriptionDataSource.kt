package com.mafraq.data.remote.dataSource.subscription.driver

import com.mafraq.data.entities.Subscriber
import kotlinx.coroutines.flow.Flow


interface DriverSubscriptionDataSource {
    val membersFlow : Flow<List<Subscriber>>
    val pendingFlow : Flow<List<Subscriber>>
    suspend fun cancel(subscriber: Subscriber)
    suspend fun accept(subscriber: Subscriber)
}
