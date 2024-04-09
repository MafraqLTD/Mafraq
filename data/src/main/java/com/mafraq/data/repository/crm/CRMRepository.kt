package com.mafraq.data.repository.crm

import com.mafraq.data.entities.map.Driver


interface CRMRepository {
    suspend fun getDrivers() : List<Driver>
}
