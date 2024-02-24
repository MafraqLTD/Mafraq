package com.mafraq.di

import com.mafraq.data.repository.FakeUserRepositoryImpl
import com.mafraq.data.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface Module {

    @Binds
    fun bindUserRepository(repository: FakeUserRepositoryImpl): UserRepository
}