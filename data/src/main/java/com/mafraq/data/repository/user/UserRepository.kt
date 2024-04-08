package com.mafraq.data.repository.user

import com.mafraq.data.entities.login.LoginBody
import com.mafraq.data.entities.login.AuthUser
import com.mafraq.data.entities.register.RegisterBody


interface UserRepository {

    suspend fun login(body: LoginBody): Boolean

    suspend fun register(body: RegisterBody): Boolean

    suspend fun logout(): Boolean

    fun isAuthorized(): Boolean

    fun getUserInfo(): AuthUser?
}
