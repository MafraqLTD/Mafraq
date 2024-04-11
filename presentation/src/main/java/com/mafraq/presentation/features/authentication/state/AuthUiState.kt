package com.mafraq.presentation.features.authentication.state

import com.mafraq.data.entities.login.LoginBody
import com.mafraq.data.entities.register.RegisterBody
import com.mafraq.presentation.features.base.ErrorState
import com.mafraq.presentation.utils.extensions.emptyString
import javax.annotation.concurrent.Immutable


@Immutable
data class AuthUiState(
    val email: String = emptyString(),
    val password: String = emptyString(),
    val confirmPassword: String = emptyString(),
    val isLoading: Boolean = false,
    val error: ErrorState? = null,
) {
    val isError: Boolean get() = error != null

    fun toLoginBody() = LoginBody(
        email = email,
        password = password,
    )

    fun toRegisterBody() = RegisterBody(
        email = email,
        password = password,
        confirmPassword = confirmPassword,
    )
}
