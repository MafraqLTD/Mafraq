package com.mafraq.data.repository.user

import com.mafraq.data.entities.login.LoginBody
import com.mafraq.data.entities.login.AuthUser
import com.mafraq.data.entities.register.RegisterBody
import com.mafraq.data.remote.dataSource.user.FirebaseAuthDataSource
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val authDataSource: FirebaseAuthDataSource
) : UserRepository {
    override suspend fun login(body: LoginBody): Boolean = authDataSource.login(body)

    override suspend fun register(body: RegisterBody): Boolean = authDataSource.register(body)

    override suspend fun logout(): Boolean = authDataSource.logout()

    override fun isAuthorized(): Boolean = authDataSource.isAuthorized()

    override fun getUserInfo(): AuthUser? = authDataSource.currentUser
}
