package com.mafraq.presentation.features.authentication.viewmodel

import com.altaie.prettycode.core.utils.extenstions.isEmail
import com.mafraq.data.repository.auth.AuthRepository
import com.mafraq.presentation.features.authentication.event.AuthEvent
import com.mafraq.presentation.features.authentication.event.LoginEvent
import com.mafraq.presentation.features.authentication.event.RegisterEvent
import com.mafraq.presentation.features.authentication.listener.AuthInteractionListener
import com.mafraq.presentation.features.authentication.state.AuthUiState
import com.mafraq.presentation.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : BaseViewModel<AuthUiState, AuthEvent>(AuthUiState()), AuthInteractionListener {

    // SHARED INTERACTIONS
    override fun setEmail(value: String) = updateState { copy(email = value, error = null) }

    override fun setPassword(value: String) = updateState { copy(password = value, error = null) }

    // REGISTER INTERACTIONS
    override fun onRegister() {
        updateState { copy(isLoading = true, error = null) }

        tryToExecute(block = { authRepository.register(body = state.value.toRegisterBody()) },
            checkSuccess = { it },
            onSuccess = {
                updateState(notifyEvent = RegisterEvent.OnRegister) {
                    copy(
                        error = null,
                        isLoading = false
                    )
                }
            },
            onError = { updateState { copy(error = it, isLoading = false) } })
    }

    override fun onNavigateToLogin() {
        updateState(notifyEvent = RegisterEvent.OnNavigateToLogin) { copy(error = null) }
    }

    override fun setConfirmPassword(value: String) =
        updateState { copy(confirmPassword = value, error = null) }

    override fun validateRegisterFields(): Boolean = state.value.run {
        listOf(
            email,
            password
        ).all(String::isNotEmpty) && password == confirmPassword && email.isEmail
    }

    // LOGIN INTERACTIONS
    override fun onLogin() {
        updateState { copy(isLoading = true, error = null) }

        tryToExecute(
            block = { authRepository.login(body = state.value.toLoginBody()) },
            checkSuccess = { it },
            onSuccess = {
                emitNewEvent(LoginEvent.OnLogin)
            },
            onError = { updateState { copy(error = it, isLoading = false) } }
        )
    }

    override fun onNavigateToRegister() {
        updateState(notifyEvent = LoginEvent.OnNavigateToRegister) { copy(error = null) }
    }

    override fun validateLoginFields(): Boolean = state.value.run {
        email.isNotEmpty() && password.isNotEmpty() && password.length >= 6 && email.isEmail
    }
}
