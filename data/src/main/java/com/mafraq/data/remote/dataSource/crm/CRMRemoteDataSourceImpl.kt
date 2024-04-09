package com.mafraq.data.remote.dataSource.crm

import com.altaie.prettycode.core.base.BaseRemoteDataSource
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.remote.mappers.DriverFromRemoteMapper
import com.mafraq.data.remote.service.RetableService
import com.mafraq.data.utils.getOrThrowEmpty
import javax.inject.Inject


class CRMRemoteDataSourceImpl @Inject constructor(
    private val apiService: RetableService,
    private val driverFromRemoteMapper: DriverFromRemoteMapper,
) : CRMRemoteDataSource, BaseRemoteDataSource {
    override suspend fun getDrivers(): List<Driver> = apiCall(
        suspendFunction = apiService::getDrivers,
        mapper = { driverFromRemoteMapper.mapList(it.data) }
    ).getOrThrowEmpty()
}
