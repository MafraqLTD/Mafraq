package com.mafraq.di

import com.mafraq.data.repository.chat.group.GroupChatRepository
import com.mafraq.data.repository.chat.group.GroupChatRepositoryImpl
import com.mafraq.data.repository.chat.support.SupportChatRepository
import com.mafraq.data.repository.chat.support.SupportChatRepositoryImpl
import com.mafraq.data.repository.crm.CRMRepository
import com.mafraq.data.repository.crm.CRMRepositoryImpl
import com.mafraq.data.repository.hardware.HardwareRepository
import com.mafraq.data.repository.hardware.HardwareRepositoryImpl
import com.mafraq.data.repository.auth.AuthRepository
import com.mafraq.data.repository.auth.AuthRepositoryImpl
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
    fun bindSupportChatDataSource(source: SupportChatRepositoryImpl): SupportChatRepository

    @Binds
    @Singleton
    fun bindGroupChatDataSource(source: GroupChatRepositoryImpl): GroupChatRepository

}
