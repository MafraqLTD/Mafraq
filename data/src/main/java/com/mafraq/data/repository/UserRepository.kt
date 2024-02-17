package com.mafraq.data.repository

import com.gateway.data.remote.entities.ApiResponse
import com.mafraq.data.entities.login.LoginBody
import com.mafraq.data.entities.login.User
import com.mafraq.data.entities.register.RegisterBody


interface UserRepository {

    suspend fun login(body: LoginBody): User

    suspend fun register(body: RegisterBody): ApiResponse

    suspend fun logout(): Boolean

    suspend fun checkAuth(): Boolean

    suspend fun getUserInfo(): User?

    fun isTokenExists(): Boolean

    suspend fun onSessionExpired()
}
