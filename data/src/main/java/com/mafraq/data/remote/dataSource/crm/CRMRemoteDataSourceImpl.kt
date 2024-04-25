package com.mafraq.data.remote.dataSource.crm

import com.altaie.prettycode.core.base.BaseRemoteDataSource
import com.mafraq.data.entities.home.Ad
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.remote.mappers.AdFromRemoteMapper
import com.mafraq.data.remote.mappers.CreateBodyFromEmployeeMapper
import com.mafraq.data.remote.mappers.DriverFromRemoteMapper
import com.mafraq.data.remote.mappers.EmployeeFromRemoteMapper
import com.mafraq.data.remote.mappers.UpdateBodyFromEmployeeMapper
import com.mafraq.data.remote.service.RetableService
import com.mafraq.data.utils.getOrThrowEmpty
import javax.inject.Inject


class CRMRemoteDataSourceImpl @Inject constructor(
    private val apiService: RetableService,
    private val adFromRemoteMapper: AdFromRemoteMapper,
    private val driverFromRemoteMapper: DriverFromRemoteMapper,
    private val employeeFromRemoteMapper: EmployeeFromRemoteMapper,
    private val createBodyFromEmployeeMapper: CreateBodyFromEmployeeMapper,
    private val updateBodyFromEmployeeMapper: UpdateBodyFromEmployeeMapper
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

    override suspend fun getEmployee(id: String): Employee = apiCall(
        suspendFunction = { apiService.getEmployee(id) },
        mapper = { employeeFromRemoteMapper.map(it.item ?: error("Employee not found")) }
    ).getOrThrowEmpty()

    override suspend fun createEmployee(value: Employee): Boolean = apiCall(
        suspendFunction = { apiService.createOrUpdateEmployee(createBodyFromEmployeeMapper.map(value)) },
        mapper = { it != null }
    ).getOrThrowEmpty()

    override suspend fun updateEmployee(value: Employee): Boolean = apiCall(
        suspendFunction = { apiService.createOrUpdateEmployee(updateBodyFromEmployeeMapper.map(value)) },
        mapper = { it != null }
    ).getOrThrowEmpty()
}
