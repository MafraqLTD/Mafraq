package com.mafraq.data.repository.crm

import com.mafraq.data.entities.Session
import com.mafraq.data.entities.home.Ad
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.entities.profile.EmployeeProfile
import com.mafraq.data.local.profile.ProfileLocalDataSource
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.remote.dataSource.crm.CRMRemoteDataSource
import com.mafraq.data.remote.mappers.EmployeeFromEmployeeProfileMapper
import javax.inject.Inject


class CRMRepositoryImpl @Inject constructor(
    private val crmRemoteDataSource: CRMRemoteDataSource,
    private val sessionLocalDataSource: SessionLocalDataSource,
    private val profileLocalDataSource: ProfileLocalDataSource,
    private val employeeFromEmployeeProfileMapper: EmployeeFromEmployeeProfileMapper
) : CRMRepository {
    override suspend fun getAds(): List<Ad> =
        crmRemoteDataSource.getAds()

    override suspend fun getDrivers(): List<Driver> =
        crmRemoteDataSource.getDrivers()

    override suspend fun getDriver(): Driver {
        val session: Session? = sessionLocalDataSource.get()
        return crmRemoteDataSource.getDriver(id = requireNotNull(session?.driverId))
    }

    override suspend fun getEmployee(): Employee {
        return profileLocalDataSource.get()
            ?: crmRemoteDataSource.getEmployee(requireNotNull(sessionLocalDataSource.get()?.userId))
                .also(profileLocalDataSource::save)
                .also {
                    sessionLocalDataSource.save(
                        driverId = it.driverId,
                        subscriptionId = it.driverId, // FIXME (NOW THE APP WORKING ON SINGLE SUBSCRIPTION)
                        userId = it.email
                    )
                }
    }

    override suspend fun saveProfile(value: EmployeeProfile): Boolean {
        val employee = employeeFromEmployeeProfileMapper.map(value)
        if (profileLocalDataSource.isProfileFilled())
            return updateEmployee(employee)
        return createEmployee(employee)
    }

    private suspend fun createEmployee(value: Employee): Boolean =
        crmRemoteDataSource.createEmployee(value)
            .also { profileLocalDataSource.save(value) }

    private suspend fun updateEmployee(value: Employee): Boolean =
        crmRemoteDataSource.updateEmployee(value)
            .also {
                val updated = getEmployee()
                profileLocalDataSource.save(updated)
            }

}
