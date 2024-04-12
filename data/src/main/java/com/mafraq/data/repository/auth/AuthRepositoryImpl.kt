package com.mafraq.data.repository.auth

import com.mafraq.data.entities.login.LoginBody
import com.mafraq.data.entities.login.AuthUser
import com.mafraq.data.entities.register.RegisterBody
import com.mafraq.data.remote.dataSource.auth.FirebaseAuthDataSource
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: FirebaseAuthDataSource
) : AuthRepository {
    override suspend fun login(body: LoginBody): Boolean = authDataSource.login(body)

    override suspend fun register(body: RegisterBody): Boolean = authDataSource.register(body)

    override suspend fun logout(): Boolean = authDataSource.logout()

    override fun isAuthorized(): Boolean = authDataSource.isAuthorized()

    override fun getUserInfo(): AuthUser? = authDataSource.currentUser
}
