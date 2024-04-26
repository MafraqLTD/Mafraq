package com.mafraq.presentation.features.home


interface HomeInteractionListener {
    fun navigateToMap()
    fun onFindDriver()
    fun navigateToSearch()
    fun navigateToSupportChat()

    object Preview : HomeInteractionListener{
        override fun onFindDriver() {}
        override fun navigateToMap() {}
        override fun navigateToSearch() {}
        override fun navigateToSupportChat() {}
    }
}
