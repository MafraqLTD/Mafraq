package com.mafraq.data.repository.crm

import com.mafraq.data.entities.home.Ad
import com.mafraq.data.entities.map.Driver


interface CRMRepository {
    suspend fun getAds() : List<Ad>
    suspend fun getDrivers() : List<Driver>
}
