package com.mafraq.employee.di

import com.mafraq.data.entities.AppUserType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppTypeModule {
    @Provides
    @Singleton
    fun provideAppUserType(): AppUserType = AppUserType.Employee
}
