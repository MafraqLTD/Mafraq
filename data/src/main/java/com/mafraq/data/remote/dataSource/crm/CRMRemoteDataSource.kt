package com.mafraq.data.remote.dataSource.crm

import com.mafraq.data.entities.home.Ad
import com.mafraq.data.entities.map.Driver


interface CRMRemoteDataSource {
    suspend fun getAds() : List<Ad>
    suspend fun getDrivers() : List<Driver>
    suspend fun getDriver(id: String) : Driver
}
