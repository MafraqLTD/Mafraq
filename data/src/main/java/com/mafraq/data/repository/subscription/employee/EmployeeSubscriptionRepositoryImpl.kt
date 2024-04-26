package com.mafraq.data.repository.subscription.employee

import com.mafraq.data.remote.dataSource.subscription.employee.EmployeeSubscriptionDataSource
import javax.inject.Inject


class EmployeeSubscriptionRepositoryImpl @Inject constructor(
    private val employeeSubscriptionDataSource: EmployeeSubscriptionDataSource
): EmployeeSubscriptionRepository {
    override suspend fun request(driverId: String) =
        employeeSubscriptionDataSource.request(driverId)

    override suspend fun cancel(driverId: String) =
        employeeSubscriptionDataSource.cancel(driverId)
}
