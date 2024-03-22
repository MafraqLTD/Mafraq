package com.mafraq.presentation.features.notifications


interface NotificationsInteractionListener {
    fun refreshNotifications()
    fun onNotificationClick(id: String)

    object Preview : NotificationsInteractionListener {
        override fun refreshNotifications() = Unit
        override fun onNotificationClick(id: String) = Unit
    }
}
