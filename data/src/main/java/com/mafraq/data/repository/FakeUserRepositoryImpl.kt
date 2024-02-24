package com.mafraq.data.repository

import com.gateway.data.remote.entities.ApiResponse
import com.mafraq.data.entities.login.LoginBody
import com.mafraq.data.entities.login.User
import com.mafraq.data.entities.register.RegisterBody
import javax.inject.Inject

class FakeUserRepositoryImpl @Inject constructor() : UserRepository {
    override suspend fun login(body: LoginBody): User {
        TODO("Not yet implemented")
    }

    override suspend fun register(body: RegisterBody): ApiResponse {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun checkAuth(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getUserInfo(): User? {
        TODO("Not yet implemented")
    }

    override fun isTokenExists(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun onSessionExpired() {
        TODO("Not yet implemented")
    }
}