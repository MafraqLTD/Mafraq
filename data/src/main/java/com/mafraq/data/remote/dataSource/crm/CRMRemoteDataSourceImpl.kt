package com.mafraq.data.remote.dataSource.crm

import com.mafraq.data.entities.home.Ad
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.remote.dataSource.BaseRemoteDataSource
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

    override suspend fun getDriverAds(): List<Ad> = apiCall(
        suspendFunction = apiService::getAds,
        mapper = { adFromRemoteMapper.mapList(it.items).filter(Ad::isDriverAd) }
    ).getOrThrowEmpty()

    override suspend fun getEmployeeAds(): List<Ad> = apiCall(
        suspendFunction = apiService::getAds,
        mapper = { adFromRemoteMapper.mapList(it.items).filterNot(Ad::isDriverAd) }
    ).getOrThrowEmpty()

    override suspend fun getDrivers(): List<Driver> = apiCall(
        suspendFunction = apiService::getDrivers,
        mapper = { driverFromRemoteMapper.mapList(it.items) }
    ).getOrThrowEmpty()

    override suspend fun getDriver(email: String): Driver = apiCall(
        suspendFunction = { apiService.getDriver() },
        mapper = { response ->
            driverFromRemoteMapper.find(response.items) { email in listOf(it.id, it.email) }
        }
    ).getOrThrowEmpty()

    override suspend fun getEmployee(email: String): Employee = apiCall(
        suspendFunction = { apiService.getEmployees() },
        mapper = { response ->
            employeeFromRemoteMapper.find(response.items) { email in listOf(it.id, it.email) }
        }
    ).getOrThrowEmpty()

    override suspend fun createEmployee(value: Employee): Boolean = apiCall(
        suspendFunction = { apiService.createEmployee(createBodyFromEmployeeMapper.map(value)) },
        mapper = { it != null }
    ).getOrThrowEmpty()

    override suspend fun updateEmployee(value: Employee): Boolean = apiCall(
        suspendFunction = { apiService.updateEmployee(updateBodyFromEmployeeMapper.map(value)) },
        mapper = { it != null }
    ).getOrThrowEmpty()
}
