package com.mafraq.presentation.features.authentication.listener


interface RegisterInteractionListener {
    fun onRegister()
    fun onNavigateToLogin()
    fun setEmail(value: String)
    fun setPassword(value: String)
    fun setConfirmPassword(value: String)
    fun validateRegisterFields(): Boolean

    object PreviewInstance: RegisterInteractionListener {
        override fun onRegister() {}
        override fun onNavigateToLogin() {}
        override fun setEmail(value: String) {}
        override fun setPassword(value: String) {}
        override fun setConfirmPassword(value: String) {}
        override fun validateRegisterFields(): Boolean = true
    }
}
