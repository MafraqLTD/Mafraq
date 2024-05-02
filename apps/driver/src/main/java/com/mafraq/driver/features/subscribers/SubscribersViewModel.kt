package com.mafraq.driver.features.subscribers

import com.mafraq.data.entities.Subscriber
import com.mafraq.data.entities.map.Location
import com.mafraq.data.repository.subscription.driver.DriverSubscriptionRepository
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.utils.location.LocationSettingsDelegate
import com.mafraq.presentation.utils.location.LocationSettingsDelegateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SubscribersViewModel @Inject constructor(
    private val locationSettingsDelegate: LocationSettingsDelegateImpl,
    private val driverSubscriptionRepository: DriverSubscriptionRepository,
) : BaseViewModel<SubscribersUiState, SubscribersEvent>(
    initState = SubscribersUiState(
        pendingFlow = driverSubscriptionRepository.membersFlow
    )
), SubscribersInteractionListener,
    LocationSettingsDelegate by locationSettingsDelegate {

    var mapDestination: Location? = null

    override fun navigateToMap(subscriber: Subscriber) {
        driverSubscriptionRepository.select(subscriber)
        mapDestination = subscriber.homeLocation
        emitNewEvent(SubscribersEvent.NavigateToMap)
    }

    override fun navigateToGroupChat() {
        emitNewEvent(SubscribersEvent.NavigateToChatGroup)
    }

    override fun unsubscribe(subscriber: Subscriber) {
        tryToExecute(
            block = { driverSubscriptionRepository.unsubscribe(subscriber) },
            onSuccess = {

            },
            onError = {
                // TODO ( HANDLE ERROR STATE )
            }
        )
    }
}
