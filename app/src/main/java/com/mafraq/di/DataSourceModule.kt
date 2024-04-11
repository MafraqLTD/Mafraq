package com.mafraq.di

import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.local.session.SessionLocalDataSourceImpl
import com.mafraq.data.remote.dataSource.crm.CRMRemoteDataSource
import com.mafraq.data.remote.dataSource.crm.CRMRemoteDataSourceImpl
import com.mafraq.data.remote.dataSource.user.FirebaseAuthDataSource
import com.mafraq.data.remote.dataSource.user.FirebaseAuthDataSourceImpl
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

}
