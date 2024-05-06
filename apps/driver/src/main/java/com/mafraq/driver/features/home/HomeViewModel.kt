package com.mafraq.driver.features.home

import com.altaie.prettycode.core.utils.extenstions.isTrue
import com.mafraq.data.entities.Subscriber
import com.mafraq.data.entities.map.Location
import com.mafraq.data.repository.auth.AuthRepository
import com.mafraq.data.repository.crm.CRMRepository
import com.mafraq.data.repository.subscription.driver.DriverSubscriptionRepository
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

    var mapDestination: Location? = null

    init {
        initialization()
    }

    override fun navigateToMap(subscriber: Subscriber) {
        driverSubscriptionRepository.select(subscriber)
        mapDestination = subscriber.homeLocation
        emitNewEvent(HomeEvent.NavigateToMap)
    }

    override fun navigateToGroupChat() {
        emitNewEvent(HomeEvent.NavigateToChatGroup)
    }

    override fun navigateToSupportChat() {
        emitNewEvent(HomeEvent.NavigateToSupportChat)
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
