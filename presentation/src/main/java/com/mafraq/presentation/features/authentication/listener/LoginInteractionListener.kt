package com.mafraq.presentation.features.authentication.listener


interface LoginInteractionListener {
    fun onLogin()
    fun onNavigateToRegister()
    fun setUsername(value: String)
    fun setPassword(value: String)
    fun setRememberMe(value: Boolean)
    fun validateLoginFields(): Boolean

    object PreviewInstance: LoginInteractionListener {
        override fun onLogin() {}
        override fun onNavigateToRegister() {}
        override fun setUsername(value: String) {}
        override fun setPassword(value: String) {}
        override fun setRememberMe(value: Boolean) {}
        override fun validateLoginFields(): Boolean = true

    }
}
