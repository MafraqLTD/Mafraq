package com.mafraq.employee.features

import com.mafraq.data.repository.auth.AuthRepository
import com.mafraq.data.repository.crm.CRMRepository
import com.mafraq.presentation.features.authentication.event.LoginEvent
import com.mafraq.presentation.features.authentication.event.RegisterEvent
import com.mafraq.presentation.features.authentication.viewmodel.AuthViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class EmployeeAuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val crmRepository: CRMRepository
): AuthViewModel() {

    override fun onRegister() {
        updateState { copy(isLoading = true, error = null) }

        tryToExecute(
            block = {
                authRepository.register(body = state.value.toRegisterBody()).also {
                    runCatching { crmRepository.getEmployee() }
                }
            },
            checkSuccess = { it },
            onSuccess = {
                val event = if (authRepository.isProfileFilled)
                    RegisterEvent.OnRegister
                else
                    RegisterEvent.OnNavigateToLoginProfile
                updateState(notifyEvent = event) {
                    copy(
                        error = null,
                        isLoading = false
                    )
                }
            },
            onError = { updateState { copy(error = it, isLoading = false) } })
    }

    override fun onLogin() {
        updateState { copy(isLoading = true, error = null) }

        tryToExecute(
            block = {
                authRepository.login(body = state.value.toLoginBody()).also {
                    runCatching { crmRepository.getEmployee() }
                }
            },
            checkSuccess = { it },
            onSuccess = {
                val event = if (authRepository.isProfileFilled)
                    LoginEvent.OnLogin
                else
                    LoginEvent.OnNavigateToLoginProfile
                updateState(notifyEvent = event) {
                    copy(
                        error = null,
                        isLoading = false
                    )
                }
            },
            onError = { updateState { copy(error = it, isLoading = false) } }
        )
    }

}
