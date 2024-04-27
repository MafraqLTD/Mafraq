package com.mafraq.data.remote.dataSource.subscription.employee

import com.mafraq.data.entities.map.Driver


interface EmployeeSubscriptionDataSource {
    suspend fun request(driver: Driver)
    suspend fun cancel()
    val driver: Driver?
}
