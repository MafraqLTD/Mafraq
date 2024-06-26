package com.mafraq.data.remote.dataSource.crm

import com.mafraq.data.entities.home.Ad
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.profile.Employee


interface CRMRemoteDataSource {
    suspend fun getDriverAds() : List<Ad>
    suspend fun getEmployeeAds() : List<Ad>
    suspend fun getDrivers() : List<Driver>
    suspend fun getDriver(email: String) : Driver
    suspend fun getEmployee(email: String) : Employee
    suspend fun createDriver(value: Driver) : Boolean
    suspend fun updateDriver(value: Driver) : Boolean
    suspend fun createEmployee(value: Employee) : Boolean
    suspend fun updateEmployee(value: Employee) : Boolean
}
