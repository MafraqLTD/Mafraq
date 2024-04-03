package com.mafraq.data.repository.user

import com.mafraq.data.entities.ApiResponse
import com.mafraq.data.entities.login.LoginBody
import com.mafraq.data.entities.login.User
import com.mafraq.data.entities.register.RegisterBody
import javax.inject.Inject


class FakeUserRepositoryImpl @Inject constructor() : UserRepository {
    private val user = User(id = 6400, name = "Anya")
    private var authorized = false

    override suspend fun login(body: LoginBody): User {
        authorized = true
        return user
    }

    override suspend fun register(body: RegisterBody): ApiResponse {
        authorized = true
        return ApiResponse(isSuccess = true, user = user)
    }

    override suspend fun logout(): Boolean {
        authorized = false
        return true
    }

    override suspend fun checkAuth(): Boolean = authorized

    override suspend fun getUserInfo(): User = user

    override fun isTokenExists(): Boolean = authorized

    override suspend fun onSessionExpired() {
        authorized = false
    }
}
