package com.mafraq.employee.features.home


interface HomeInteractionListener {
    fun navigateToMap()
    fun onFindDriver()
    fun navigateToSearch()
    fun navigateToGroupChat()
    fun navigateToSupportChat()
    fun cancelSubscribeRequest()
    fun reload()

    object Preview : HomeInteractionListener {
        override fun reload() {}
        override fun onFindDriver() {}
        override fun navigateToMap() {}
        override fun navigateToSearch() {}
        override fun navigateToGroupChat() {}
        override fun navigateToSupportChat() {}
        override fun cancelSubscribeRequest() {}
    }
}
