package com.mafraq.data.repository.subscription.driver

import com.mafraq.data.entities.Subscriber
import com.mafraq.data.remote.dataSource.subscription.driver.DriverSubscriptionDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.single
import javax.inject.Inject


class DriverSubscriptionRepositoryImpl @Inject constructor(
    private val driverSubscriptionDataSource: DriverSubscriptionDataSource
) : DriverSubscriptionRepository {
    private var selectedSubscriber: Subscriber? = null
    override val membersFlow: Flow<List<Subscriber>> =
        driverSubscriptionDataSource.membersFlow

    override val pendingFlow: Flow<List<Subscriber>> =
        driverSubscriptionDataSource.pendingFlow

    override suspend fun hasSubscribers(): Boolean =
        driverSubscriptionDataSource.hasSubscribers()

    override fun select(subscriber: Subscriber?) {
        selectedSubscriber = subscriber
    }

    override suspend fun cancel(subscriber: Subscriber) =
        driverSubscriptionDataSource.cancel(subscriber)

    override suspend fun accept() = driverSubscriptionDataSource.accept(
        subscriber = selectedSubscriber ?: error("No Subscriber Selected!")
    )
}
