package com.mafraq.employee.di

import com.mafraq.data.repository.auth.AuthRepository
import com.mafraq.data.repository.auth.AuthRepositoryImpl
import com.mafraq.data.repository.chat.group.GroupChatRepository
import com.mafraq.data.repository.chat.group.GroupChatRepositoryImpl
import com.mafraq.data.repository.chat.support.SupportChatRepository
import com.mafraq.data.repository.chat.support.SupportChatRepositoryImpl
import com.mafraq.data.repository.crm.CRMRepository
import com.mafraq.data.repository.crm.CRMRepositoryImpl
import com.mafraq.data.repository.hardware.HardwareRepository
import com.mafraq.data.repository.hardware.HardwareRepositoryImpl
import com.mafraq.data.repository.map.MapPlacesRepository
import com.mafraq.data.repository.map.MapPlacesRepositoryImpl
import com.mafraq.data.repository.subscription.driver.DriverSubscriptionRepository
import com.mafraq.data.repository.subscription.driver.DriverSubscriptionRepositoryImpl
import com.mafraq.data.repository.subscription.employee.EmployeeSubscriptionRepository
import com.mafraq.data.repository.subscription.employee.EmployeeSubscriptionRepositoryImpl
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
    fun bindUserRepository(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindHardwareRepository(repository: HardwareRepositoryImpl): HardwareRepository

    @Binds
    @Singleton
    fun bindCRMRepository(repository: CRMRepositoryImpl): CRMRepository

    @Binds
    @Singleton
    fun bindSupportChatRepository(repository: SupportChatRepositoryImpl): SupportChatRepository

    @Binds
    @Singleton
    fun bindGroupChatRepository(repository: GroupChatRepositoryImpl): GroupChatRepository
    
    @Binds
    @Singleton
    fun bindMapPlacesRepository(repository: MapPlacesRepositoryImpl): MapPlacesRepository

    @Binds
    @Singleton
    fun bindEmployeeSubscriptionRepository(repository: EmployeeSubscriptionRepositoryImpl): EmployeeSubscriptionRepository

    @Binds
    @Singleton
    fun bindDriverSubscriptionRepository(repository: DriverSubscriptionRepositoryImpl): DriverSubscriptionRepository

}
