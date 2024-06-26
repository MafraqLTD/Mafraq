package com.mafraq.presentation.features.authentication.viewmodel

import com.altaie.prettycode.core.utils.extenstions.isEmail
import com.mafraq.data.repository.auth.AuthRepository
import com.mafraq.data.repository.crm.CRMRepository
import com.mafraq.presentation.features.authentication.event.AuthEvent
import com.mafraq.presentation.features.authentication.event.LoginEvent
import com.mafraq.presentation.features.authentication.event.RegisterEvent
import com.mafraq.presentation.features.authentication.listener.LoginInteractionListener
import com.mafraq.presentation.features.authentication.listener.RegisterInteractionListener
import com.mafraq.presentation.features.authentication.state.AuthUiState
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.utils.validation.ValidationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



abstract class AuthViewModel : BaseViewModel<AuthUiState, AuthEvent>(AuthUiState()),
    RegisterInteractionListener, LoginInteractionListener {

    // SHARED INTERACTIONS
    override fun setEmail(value: String) {
        updateState { copy(email = value, error = null) }
        validateEmail()
    }

    override fun setPassword(value: String) {
        updateState { copy(password = value, error = null) }
        validatePassword()
    }

    private fun validateEmail(): ValidationState = state.value.email.run {
        when {
            isEmpty() -> ValidationState.Empty
            isEmail -> ValidationState.Valid
            else -> ValidationState.Invalid
        }.also { updateState { copy(isEmailInvalid = it.isInvalid) } }
    }

    private fun validatePassword(): ValidationState = state.value.password.run {
        when {
            isEmpty() -> ValidationState.Empty
            length >= PASSWORD_MIN_LENGTH -> ValidationState.Valid
            else -> ValidationState.Invalid
        }.also { updateState { copy(isPasswordInvalid = it.isInvalid) } }
    }

    private fun validateFields(isRegister: Boolean = false): ValidationState {
        val emailValidation = validateEmail()
        val passwordValidation = validatePassword()
        val confirmPasswordValidation = if (isRegister)
            validateConfirmPassword()
        else
            passwordValidation

        return when {
            emailValidation.isEmpty
                    && passwordValidation.isEmpty
                    && confirmPasswordValidation.isEmpty -> ValidationState.Empty

            emailValidation.isValid
                    && passwordValidation.isValid
                    && confirmPasswordValidation.isValid -> ValidationState.Valid

            else -> ValidationState.Invalid
        }
    }

    // REGISTER INTERACTIONS
    private fun validateConfirmPassword(): ValidationState = state.value.confirmPassword.run {
        when {
            isEmpty() -> ValidationState.Empty
            (length >= PASSWORD_MIN_LENGTH)
                    && (state.value.password == this) -> ValidationState.Valid

            else -> ValidationState.Invalid
        }.also { updateState { copy(isConfirmPasswordInvalid = it.isInvalid) } }
    }

    override fun onNavigateToLogin() {
        updateState(notifyEvent = RegisterEvent.OnNavigateToLogin) { copy(error = null) }
    }

    override fun setConfirmPassword(value: String) {
        updateState { copy(confirmPassword = value, error = null) }
        validateConfirmPassword()
    }

    override fun validateRegisterFields(): ValidationState = validateFields(isRegister = true)

    // LOGIN INTERACTIONS

    override fun onNavigateToRegister() {
        updateState(notifyEvent = LoginEvent.OnNavigateToRegister) { copy(error = null) }
    }

    override fun validateLoginFields(): ValidationState = validateFields()

    companion object {
        const val PASSWORD_MIN_LENGTH = 6
    }
}
