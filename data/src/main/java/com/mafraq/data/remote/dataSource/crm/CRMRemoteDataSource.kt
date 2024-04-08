package com.mafraq.data.remote.dataSource.crm

import com.mafraq.data.entities.map.Driver


interface CRMRemoteDataSource {
    suspend fun getDrivers() : List<Driver>
}
