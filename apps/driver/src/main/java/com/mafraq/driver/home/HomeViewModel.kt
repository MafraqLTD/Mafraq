package com.mafraq.driver.home

import com.altaie.prettycode.core.utils.extenstions.isTrue
import com.mafraq.data.entities.Subscriber
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.repository.auth.AuthRepository
import com.mafraq.data.repository.crm.CRMRepository
import com.mafraq.data.repository.subscription.driver.DriverSubscriptionRepository
import com.mafraq.data.repository.subscription.employee.EmployeeSubscriptionRepository
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.utils.location.LocationSettingsDelegate
import com.mafraq.presentation.utils.location.LocationSettingsDelegateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val crmRepository: CRMRepository,
    private val locationSettingsDelegate: LocationSettingsDelegateImpl,
    private val driverSubscriptionRepository: DriverSubscriptionRepository,
) : BaseViewModel<HomeUiState, HomeEvent>(
    initState = HomeUiState(
        pendingFlow = driverSubscriptionRepository.pendingFlow
    )
), HomeInteractionListener,
    LocationSettingsDelegate by locationSettingsDelegate {

    init {
        initialization()
    }

    override fun navigateToMap() {
        emitNewEvent(HomeEvent.NavigateToMap)
    }

    override fun navigateToGroupChat() {
        emitNewEvent(HomeEvent.NavigateToChatGroup)
    }

    override fun navigateToSupportChat() {
        emitNewEvent(HomeEvent.NavigateToSupportChat)
    }

    override fun acceptSubscribeRequest(subscriber: Subscriber) {
        tryToExecute(
            block = { driverSubscriptionRepository.accept(subscriber) },
            onSuccess = {

            },
            onError = {
                // TODO ( HANDLE ERROR STATE )
            }
        )
    }

    val isEmailVerified: Boolean
        get() = authRepository.getUserInfo()?.isEmailVerified.isTrue

    fun onVerificationDone() {
        authRepository.isAuthorized()
    }

    override fun cancelSubscribeRequest(subscriber: Subscriber) {
        tryToExecute(
            block = { driverSubscriptionRepository.cancel(subscriber) },
            onSuccess = {

            },
            onError = {
                // TODO ( HANDLE ERROR STATE )
            }
        )
    }

    private fun initialization() {
        tryToExecute(
            block = crmRepository::getDriverAds,
            onSuccess = {
                updateState { copy(ads = it) }
            },
        )
    }
}
