package com.mafraq.data.repository.crm

import com.mafraq.data.entities.Session
import com.mafraq.data.entities.home.Ad
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.remote.dataSource.crm.CRMRemoteDataSource
import javax.inject.Inject


class CRMRepositoryImpl @Inject constructor(
    private val crmRemoteDataSource: CRMRemoteDataSource,
    private val sessionLocalDataSource: SessionLocalDataSource
): CRMRepository {
    override suspend fun getAds(): List<Ad> =
        crmRemoteDataSource.getAds()

    override suspend fun getDrivers(): List<Driver> =
        crmRemoteDataSource.getDrivers()

    override suspend fun getDriver(): Driver {
        val session: Session? = sessionLocalDataSource.get()
        return crmRemoteDataSource.getDriver(id = requireNotNull(session?.driverId))
    }
}
