package com.mafraq.data.repository.auth

import com.mafraq.data.entities.AppUserType
import com.mafraq.data.entities.login.LoginBody
import com.mafraq.data.entities.login.AuthUser
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.entities.register.RegisterBody
import com.mafraq.data.local.profile.ProfileLocalDataSource
import com.mafraq.data.remote.dataSource.auth.FirebaseAuthDataSource
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    appUserType: AppUserType,
    private val authDataSource: FirebaseAuthDataSource,
    driverProfileLocalDataSource: ProfileLocalDataSource<Driver>,
    employeeProfileLocalDataSource: ProfileLocalDataSource<Employee>,
) : AuthRepository {
    val profileLocalDataSource = if (appUserType.isDriverApp)
        driverProfileLocalDataSource
    else
        employeeProfileLocalDataSource

    override val isProfileFilled: Boolean
        get() = profileLocalDataSource.isProfileFilled()

    override suspend fun login(body: LoginBody): Boolean = authDataSource.login(body)

    override suspend fun register(body: RegisterBody): Boolean = authDataSource.register(body)

    override fun logout() = authDataSource.logout()

    override fun isAuthorized(): Boolean = authDataSource.isAuthorized()

    override fun getUserInfo(): AuthUser? = authDataSource.currentUser
}
