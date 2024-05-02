package com.mafraq.data.repository.subscription.employee

import com.mafraq.data.entities.map.Driver
import com.mafraq.data.remote.dataSource.subscription.employee.SubscribeRequestStatus
import kotlinx.coroutines.flow.Flow


interface EmployeeSubscriptionRepository {
    val pendingDriver: Driver?
    suspend fun request(driver: Driver)
    suspend fun cancel()
    val subscribeRequestStatusFlow: Flow<SubscribeRequestStatus>
}
