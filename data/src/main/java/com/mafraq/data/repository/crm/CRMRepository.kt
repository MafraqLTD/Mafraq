package com.mafraq.data.repository.crm

import com.mafraq.data.entities.home.Ad
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.entities.profile.GeneralProfile


interface CRMRepository {
    suspend fun getDriverAds() : List<Ad>
    suspend fun getEmployeeAds() : List<Ad>
    suspend fun getDrivers() : List<Driver>
    suspend fun getDriver() : Driver
    suspend fun getEmployee() : Employee
    suspend fun saveDriverProfile(value: GeneralProfile) : Boolean
    suspend fun saveEmployeeProfile(value: GeneralProfile) : Boolean
}
