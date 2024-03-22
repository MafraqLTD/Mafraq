package com.mafraq.presentation.features.notifications

import com.mafraq.presentation.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class NotificationsViewModel @Inject constructor(

) : BaseViewModel<NotificationsUiState, NotificationsEvent>(NotificationsUiState()),
    NotificationsInteractionListener {
    override fun refreshNotifications() {
        // TODO("Not yet implemented")
    }

    override fun onNotificationClick(id: String) {
        // TODO("Not yet implemented")
    }

}
