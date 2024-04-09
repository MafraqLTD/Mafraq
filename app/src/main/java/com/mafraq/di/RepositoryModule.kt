package com.mafraq.di

import com.mafraq.data.repository.crm.CRMRepository
import com.mafraq.data.repository.crm.CRMRepositoryImpl
import com.mafraq.data.repository.hardware.HardwareRepository
import com.mafraq.data.repository.hardware.HardwareRepositoryImpl
import com.mafraq.data.repository.user.UserRepository
import com.mafraq.data.repository.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    fun bindHardwareRepository(repository: HardwareRepositoryImpl): HardwareRepository

    @Binds
    @Singleton
    fun bindCRMRepository(repository: CRMRepositoryImpl): CRMRepository

}
