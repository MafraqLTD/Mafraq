package com.mafraq.data.repository.crm

import com.mafraq.data.entities.Session
import com.mafraq.data.entities.home.Ad
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.local.profile.ProfileLocalDataSource
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.remote.dataSource.crm.CRMRemoteDataSource
import javax.inject.Inject


class CRMRepositoryImpl @Inject constructor(
    private val crmRemoteDataSource: CRMRemoteDataSource,
    private val sessionLocalDataSource: SessionLocalDataSource,
    private val profileLocalDataSource: ProfileLocalDataSource
) : CRMRepository {
    override suspend fun getAds(): List<Ad> =
        crmRemoteDataSource.getAds()

    override suspend fun getDrivers(): List<Driver> =
        crmRemoteDataSource.getDrivers()

    override suspend fun getDriver(): Driver {
        val session: Session? = sessionLocalDataSource.get()
        return crmRemoteDataSource.getDriver(id = requireNotNull(session?.driverId))
    }

    override suspend fun getEmployee(): Employee =
        profileLocalDataSource.get()
            ?: crmRemoteDataSource.getEmployee(requireNotNull(sessionLocalDataSource.get()?.userId))
                .also(profileLocalDataSource::save)

    override suspend fun createEmployee(): Employee =
    createOrUpdateEmployee(crmRemoteDataSource::createEmployee)

    override suspend fun updateEmployee(): Employee =
        createOrUpdateEmployee(crmRemoteDataSource::updateEmployee)

    private suspend fun createOrUpdateEmployee(block: suspend (Employee) -> Employee): Employee =
        block(requireNotNull(profileLocalDataSource.get()))
                .also(profileLocalDataSource::save)
}
