package com.mafraq.data.remote.dataSource.subscription.employee


interface EmployeeSubscriptionDataSource {
    suspend fun request(driverId: String)
    suspend fun cancel(driverId: String)
}
