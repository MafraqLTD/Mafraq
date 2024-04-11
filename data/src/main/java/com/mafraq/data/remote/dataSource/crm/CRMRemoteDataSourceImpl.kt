package com.mafraq.data.remote.dataSource.crm

import com.altaie.prettycode.core.base.BaseRemoteDataSource
import com.mafraq.data.entities.home.Ad
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.remote.mappers.AdFromRemoteMapper
import com.mafraq.data.remote.mappers.DriverFromRemoteMapper
import com.mafraq.data.remote.service.RetableService
import com.mafraq.data.utils.getOrThrowEmpty
import javax.inject.Inject


class CRMRemoteDataSourceImpl @Inject constructor(
    private val apiService: RetableService,
    private val adFromRemoteMapper: AdFromRemoteMapper,
    private val driverFromRemoteMapper: DriverFromRemoteMapper,
) : CRMRemoteDataSource, BaseRemoteDataSource {
    override suspend fun getAds(): List<Ad> = apiCall(
        suspendFunction = apiService::getAds,
        mapper = { adFromRemoteMapper.mapList(it.items) }
    ).getOrThrowEmpty()

    override suspend fun getDrivers(): List<Driver> = apiCall(
        suspendFunction = apiService::getDrivers,
        mapper = { driverFromRemoteMapper.mapList(it.items) }
    ).getOrThrowEmpty()

    override suspend fun getDriver(id: String): Driver = apiCall(
        suspendFunction = { apiService.getDriver(id) },
        mapper = { driverFromRemoteMapper.map(it.item ?: error("Driver not found")) }
    ).getOrThrowEmpty()
}
