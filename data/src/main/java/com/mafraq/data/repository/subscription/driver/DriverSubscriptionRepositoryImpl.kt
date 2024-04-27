package com.mafraq.data.repository.subscription.driver

import com.mafraq.data.entities.Subscriber
import com.mafraq.data.remote.dataSource.subscription.driver.DriverSubscriptionDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class DriverSubscriptionRepositoryImpl @Inject constructor(
    private val driverSubscriptionDataSource: DriverSubscriptionDataSource
): DriverSubscriptionRepository {
    override val membersFlow: Flow<List<Subscriber>> =
        driverSubscriptionDataSource.membersFlow

    override val pendingFlow: Flow<List<Subscriber>> =
        driverSubscriptionDataSource.pendingFlow

    override suspend fun cancel(subscriber: Subscriber) =
        driverSubscriptionDataSource.cancel(subscriber)

    override suspend fun accept(subscriber: Subscriber) =
        driverSubscriptionDataSource.accept(subscriber)
}
