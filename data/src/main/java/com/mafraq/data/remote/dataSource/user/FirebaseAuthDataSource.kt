package com.mafraq.data.remote.dataSource.user

import com.mafraq.data.entities.login.LoginBody
import com.mafraq.data.entities.login.User
import com.mafraq.data.entities.register.RegisterBody


interface FirebaseAuthDataSource {
    val currentUser: User?
    suspend fun login(body: LoginBody): Boolean
    suspend fun register(body: RegisterBody): Boolean
    suspend fun logout(): Boolean
    suspend fun updateUser(user: User): Boolean
    fun isAuthorized(): Boolean
}
