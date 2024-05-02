package com.mafraq.data.repository.subscription.employee

import com.mafraq.data.entities.map.Driver
import com.mafraq.data.remote.dataSource.subscription.employee.EmployeeSubscriptionDataSource
import com.mafraq.data.remote.dataSource.subscription.employee.SubscribeRequestStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class EmployeeSubscriptionRepositoryImpl @Inject constructor(
    private val employeeSubscriptionDataSource: EmployeeSubscriptionDataSource
): EmployeeSubscriptionRepository {
    override val pendingDriver: Driver?
        get() = employeeSubscriptionDataSource.driver

    override suspend fun request(driver: Driver) =
        employeeSubscriptionDataSource.request(driver)

    override suspend fun cancel() =
        employeeSubscriptionDataSource.cancel()

    override val subscribeRequestStatusFlow: Flow<SubscribeRequestStatus> =
        employeeSubscriptionDataSource.subscribeRequestStatusFlow
}
