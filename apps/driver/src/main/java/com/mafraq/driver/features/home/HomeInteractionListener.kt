package com.mafraq.driver.features.home

import com.mafraq.data.entities.Subscriber
import com.mafraq.data.entities.map.Location


interface HomeInteractionListener {
    fun navigateToGroupChat()
    fun navigateToSupportChat()
    fun navigateToMap(subscriber: Subscriber)
    fun cancelSubscribeRequest(subscriber: Subscriber)

    object Preview : HomeInteractionListener {
        override fun navigateToGroupChat() {}
        override fun navigateToSupportChat() {}
        override fun navigateToMap(subscriber: Subscriber) {}
        override fun cancelSubscribeRequest(subscriber: Subscriber) {}
    }
}
