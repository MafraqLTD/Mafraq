package com.mafraq.data.repository.crm

import com.mafraq.data.entities.home.Ad
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.profile.Employee


interface CRMRepository {
    suspend fun getAds() : List<Ad>
    suspend fun getDrivers() : List<Driver>
    suspend fun getDriver() : Driver
    suspend fun getEmployee() : Employee
    suspend fun createEmployee() : Employee
    suspend fun updateEmployee() : Employee
}
