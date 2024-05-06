package com.mafraq.employee.features.auth

import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.repository.auth.AuthRepository
import com.mafraq.data.repository.crm.CRMRepository
import com.mafraq.employee.main.components.AppState
import com.mafraq.presentation.features.authentication.event.LoginEvent
import com.mafraq.presentation.features.authentication.event.RegisterEvent
import com.mafraq.presentation.features.authentication.viewmodel.AuthViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class EmployeeAuthViewModel @Inject constructor(
    private val appState: AppState,
    private val authRepository: AuthRepository,
    private val crmRepository: CRMRepository,
) : AuthViewModel() {

    override fun onRegister() {
        updateState { copy(isLoading = true, error = null) }

        tryToExecute(
            block = {
                val result = authRepository.register(body = state.value.toRegisterBody())
                crmRepository.getEmployee()
                result
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
                appState.reload()
            },
            onError = { updateState { copy(error = it, isLoading = false) } })
    }

    override fun onLogin() {
        updateState { copy(isLoading = true, error = null) }

        tryToExecute(
            block = {
                val result = authRepository.login(body = state.value.toLoginBody())
                crmRepository.getEmployee()
                result
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
                appState.reload()
            },
            onError = { updateState { copy(error = it, isLoading = false) } }
        )
    }

}
