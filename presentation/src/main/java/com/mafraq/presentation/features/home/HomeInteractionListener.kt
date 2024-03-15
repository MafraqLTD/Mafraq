package com.mafraq.presentation.features.home


interface HomeInteractionListener {
    fun onClearSearch()
    fun navigateToMap()
    fun navigateToSupportChat()
    fun onSearchQueryChange(value: String)

    object Preview : HomeInteractionListener{
        override fun onClearSearch() {}
        override fun navigateToMap() {}
        override fun navigateToSupportChat() {}
        override fun onSearchQueryChange(value: String) {}
    }
}
