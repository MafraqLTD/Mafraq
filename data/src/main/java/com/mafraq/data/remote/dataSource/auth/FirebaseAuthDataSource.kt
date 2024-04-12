package com.mafraq.data.remote.dataSource.auth

import com.mafraq.data.entities.login.LoginBody
import com.mafraq.data.entities.login.AuthUser
import com.mafraq.data.entities.register.RegisterBody


interface FirebaseAuthDataSource {
    val currentUser: AuthUser?
    suspend fun login(body: LoginBody): Boolean
    suspend fun register(body: RegisterBody): Boolean
    suspend fun logout(): Boolean
    suspend fun updateUser(user: AuthUser): Boolean
    fun isAuthorized(): Boolean
}
