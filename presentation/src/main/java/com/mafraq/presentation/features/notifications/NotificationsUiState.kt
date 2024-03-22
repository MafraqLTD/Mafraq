package com.mafraq.presentation.features.notifications

import com.mafraq.data.entities.notifications.Notification


data class NotificationsUiState(
    val notifications: List<Notification> = emptyList()
)
