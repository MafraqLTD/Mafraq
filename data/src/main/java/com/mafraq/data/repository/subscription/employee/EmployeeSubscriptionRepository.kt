package com.mafraq.data.repository.subscription.employee


interface EmployeeSubscriptionRepository {
    suspend fun request(driverId: String)
    suspend fun cancel(driverId: String)
}
