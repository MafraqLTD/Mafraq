package com.mafraq.driver.home

import com.mafraq.data.entities.Subscriber


interface HomeInteractionListener {
    fun navigateToMap()
    fun navigateToGroupChat()
    fun navigateToSupportChat()
    fun acceptSubscribeRequest(subscriber: Subscriber)
    fun cancelSubscribeRequest(subscriber: Subscriber)

    object Preview : HomeInteractionListener {
        override fun navigateToMap() {}
        override fun navigateToGroupChat() {}
        override fun navigateToSupportChat() {}
        override fun acceptSubscribeRequest(subscriber: Subscriber) {}
        override fun cancelSubscribeRequest(subscriber: Subscriber) {}
    }
}
