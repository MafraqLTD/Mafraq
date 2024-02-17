package com.mafraq.presentation.features.authentication.state

import com.mafraq.data.entities.login.LoginBody
import com.mafraq.data.entities.register.RegisterBody
import com.mafraq.presentation.features.base.ErrorState
import com.mafraq.presentation.utils.extensions.emptyString
import javax.annotation.concurrent.Immutable


@Immutable
data class AuthUiState(
    val email: String = emptyString(),
    val address: String = emptyString(),
    val username: String = emptyString(),
    val password: String = emptyString(),
    val confirmPassword: String = emptyString(),
    val phoneNumber: String = emptyString(),
    val rememberMe: Boolean = false,
    val isLoading: Boolean = false,
    val error: ErrorState? = null,
) {
    val isError: Boolean get() = error != null

    fun toLoginBody() = LoginBody(
        username = username,
        password = password,
        rememberMe = rememberMe
    )

    fun toRegisterBody() = RegisterBody(
        username = username,
        password = password,
        confirmPassword = confirmPassword,
        address = address,
        phoneNumber = phoneNumber
    )
}
