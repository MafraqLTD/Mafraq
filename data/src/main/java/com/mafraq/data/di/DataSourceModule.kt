package com.mafraq.data.di


import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.local.driver.DriverLocalDataSource
import com.mafraq.data.local.driver.DriverLocalDataSourceImpl
import com.mafraq.data.local.profile.DriverProfileLocalDataSourceImpl
import com.mafraq.data.local.profile.ProfileLocalDataSource
import com.mafraq.data.local.profile.EmployeeProfileLocalDataSourceImpl
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.local.session.SessionLocalDataSourceImpl
import com.mafraq.data.remote.dataSource.auth.FirebaseAuthDataSource
import com.mafraq.data.remote.dataSource.auth.FirebaseAuthDataSourceImpl
import com.mafraq.data.remote.dataSource.crm.CRMRemoteDataSource
import com.mafraq.data.remote.dataSource.crm.CRMRemoteDataSourceImpl
import com.mafraq.data.remote.dataSource.map.PlacesDataSource
import com.mafraq.data.remote.dataSource.map.PlacesDataSourceImpl
import com.mafraq.data.remote.dataSource.subscription.driver.DriverSubscriptionDataSource
import com.mafraq.data.remote.dataSource.subscription.driver.DriverSubscriptionDataSourceImpl
import com.mafraq.data.remote.dataSource.subscription.employee.EmployeeSubscriptionDataSource
import com.mafraq.data.remote.dataSource.subscription.employee.EmployeeSubscriptionDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindUserFirebaseAuthDataSource(source: FirebaseAuthDataSourceImpl): FirebaseAuthDataSource

    @Binds
    @Singleton
    fun bindCRMRemoteDataSource(dataSource: CRMRemoteDataSourceImpl): CRMRemoteDataSource

    @Binds
    @Singleton
    fun bindSessionLocalDataSource(source: SessionLocalDataSourceImpl): SessionLocalDataSource

    @Binds
    @Singleton
    fun bindEmployeeProfileLocalDataSource(source: EmployeeProfileLocalDataSourceImpl): ProfileLocalDataSource<Employee>

    @Binds
    @Singleton
    fun bindDriverProfileLocalDataSource(source: DriverProfileLocalDataSourceImpl): ProfileLocalDataSource<Driver>

    @Binds
    @Singleton
    fun bindMapBoxPlacesDataSource(source: PlacesDataSourceImpl): PlacesDataSource

    @Binds
    @Singleton
    fun bindDriverSubscriptionDataSource(source: DriverSubscriptionDataSourceImpl): DriverSubscriptionDataSource

    @Binds
    @Singleton
    fun bindEmployeeSubscriptionDataSource(source: EmployeeSubscriptionDataSourceImpl): EmployeeSubscriptionDataSource

    @Binds
    @Singleton
    fun bindDriverLocalDataSource(source: DriverLocalDataSourceImpl): DriverLocalDataSource

}
