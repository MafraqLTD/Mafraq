package com.mafraq.data.repository.crm

import com.mafraq.data.entities.Session
import com.mafraq.data.entities.home.Ad
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.entities.profile.GeneralProfile
import com.mafraq.data.local.profile.ProfileLocalDataSource
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.remote.dataSource.crm.CRMRemoteDataSource
import com.mafraq.data.remote.mappers.DriverFromGeneralProfileMapper
import com.mafraq.data.remote.mappers.EmployeeFromGeneralProfileMapper
import javax.inject.Inject


class CRMRepositoryImpl @Inject constructor(
    private val crmRemoteDataSource: CRMRemoteDataSource,
    private val sessionLocalDataSource: SessionLocalDataSource,
    private val driverProfileLocalDataSource: ProfileLocalDataSource<Driver>,
    private val driverFromGeneralProfileMapper: DriverFromGeneralProfileMapper,
    private val employeeProfileLocalDataSource: ProfileLocalDataSource<Employee>,
    private val employeeFromGeneralProfileMapper: EmployeeFromGeneralProfileMapper,
) : CRMRepository {
    override suspend fun getDriverAds(): List<Ad> = crmRemoteDataSource.getDriverAds()

    override suspend fun getEmployeeAds(): List<Ad> = crmRemoteDataSource.getEmployeeAds()

    override suspend fun getDrivers(): List<Driver> =
        crmRemoteDataSource.getDrivers()

    override suspend fun getDriver(): Driver {
        val session: Session? = sessionLocalDataSource.get()
        return driverProfileLocalDataSource.get()
            ?: crmRemoteDataSource.getDriver(requireNotNull(session?.email))
                .also(driverProfileLocalDataSource::save)
                .also {
                    sessionLocalDataSource.save(
                        email = it.email,
                        driverEmail = it.email
                    )
                }
    }

    override suspend fun getEmployee(): Employee {
        return employeeProfileLocalDataSource.get()
            ?: crmRemoteDataSource.getEmployee(requireNotNull(sessionLocalDataSource.get()?.email))
                .also(employeeProfileLocalDataSource::save)
                .also {
                    sessionLocalDataSource.save(
                        email = it.email,
                        driverEmail = it.driverEmail
                    )
                }
    }

    override suspend fun saveEmployeeProfile(value: GeneralProfile): Boolean {
        val driver = driverFromGeneralProfileMapper.map(value)
        if (driverProfileLocalDataSource.isProfileFilled())
            return updateDriver(driver)
        return createDriver(driver)
    }

    override suspend fun saveDriverProfile(value: GeneralProfile): Boolean {
        val employee = employeeFromGeneralProfileMapper.map(value)
        if (employeeProfileLocalDataSource.isProfileFilled())
            return updateEmployee(employee)
        return createEmployee(employee)
    }

    private suspend fun createEmployee(value: Employee): Boolean =
        crmRemoteDataSource.createEmployee(value)
            .also { employeeProfileLocalDataSource.save(value) }

    private suspend fun updateEmployee(value: Employee): Boolean =
        crmRemoteDataSource.updateEmployee(value)
            .also {
                val updated = getEmployee()
                employeeProfileLocalDataSource.save(updated)
            }

    private suspend fun createDriver(value: Driver): Boolean =
        crmRemoteDataSource.createDriver(value)
            .also { driverProfileLocalDataSource.save(value) }

    private suspend fun updateDriver(value: Driver): Boolean =
        crmRemoteDataSource.updateDriver(value)
            .also {
                val updated = getDriver()
                driverProfileLocalDataSource.save(updated)
            }
}
