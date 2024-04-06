package com.mafraq.presentation.features.authentication.listener


interface LoginInteractionListener {
    fun onLogin()
    fun onNavigateToRegister()
    fun setEmail(value: String)
    fun setPassword(value: String)
    fun validateLoginFields(): Boolean

    object PreviewInstance: LoginInteractionListener {
        override fun onLogin() {}
        override fun onNavigateToRegister() {}
        override fun setEmail(value: String) {}
        override fun setPassword(value: String) {}
        override fun validateLoginFields(): Boolean = true

    }
}
