package com.mafraq.data.repository.auth

import com.mafraq.data.entities.login.LoginBody
import com.mafraq.data.entities.login.AuthUser
import com.mafraq.data.entities.register.RegisterBody


interface AuthRepository {
    val isProfileFilled: Boolean

    suspend fun login(body: LoginBody): Boolean

    suspend fun register(body: RegisterBody): Boolean

    fun logout()

    fun isAuthorized(): Boolean

    fun getUserInfo(): AuthUser?
}
