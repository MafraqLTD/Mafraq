package com.mafraq.data.repository.subscription.employee

import com.mafraq.data.entities.map.Driver


interface EmployeeSubscriptionRepository {
    val pendingDriver: Driver?
    suspend fun request(driver: Driver)
    suspend fun cancel()
}
