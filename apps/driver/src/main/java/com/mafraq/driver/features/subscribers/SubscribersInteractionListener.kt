package com.mafraq.driver.features.subscribers

import com.mafraq.data.entities.Subscriber


interface SubscribersInteractionListener {
    fun navigateToGroupChat()
    fun navigateToMap(subscriber: Subscriber)
    fun unsubscribe(subscriber: Subscriber)

    object Preview : SubscribersInteractionListener {
        override fun navigateToGroupChat() {}
        override fun navigateToMap(subscriber: Subscriber) {}
        override fun unsubscribe(subscriber: Subscriber) {}
    }
}
