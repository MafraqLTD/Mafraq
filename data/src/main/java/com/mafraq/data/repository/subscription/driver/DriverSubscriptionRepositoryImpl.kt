package com.mafraq.data.repository.subscription.driver

import com.mafraq.data.entities.Subscriber
import com.mafraq.data.remote.dataSource.subscription.driver.DriverSubscriptionDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class DriverSubscriptionRepositoryImpl @Inject constructor(
    private val driverSubscriptionDataSource: DriverSubscriptionDataSource
) : DriverSubscriptionRepository {
    private var selectedSubscriber: Subscriber? = null

    override val subscribersFlow: Flow<List<Subscriber>> =
        driverSubscriptionDataSource.subscribersFlow

    override val pendingFlow: Flow<List<Subscriber>> =
        driverSubscriptionDataSource.pendingFlow

    override fun select(subscriber: Subscriber?) {
        selectedSubscriber = subscriber
    }

    override suspend fun cancel(subscriber: Subscriber) =
        driverSubscriptionDataSource.cancel(subscriber)

    override suspend fun unsubscribe(subscriber: Subscriber) =
        driverSubscriptionDataSource.unsubscribe(subscriber)

    override suspend fun accept() = driverSubscriptionDataSource.accept(
        subscriber = selectedSubscriber ?: error("No Subscriber Selected!")
    )
}
