package com.mafraq.data.repository.crm

import com.mafraq.data.entities.map.Driver
import com.mafraq.data.remote.dataSource.crm.CRMRemoteDataSource
import javax.inject.Inject


class CRMRepositoryImpl @Inject constructor(
    private val crmRemoteDataSource: CRMRemoteDataSource,
): CRMRepository {
    override suspend fun getDrivers(): List<Driver> =
        crmRemoteDataSource.getDrivers()
}
