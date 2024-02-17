package com.mafraq.presentation.features.authentication.listener


interface RegisterInteractionListener {
    fun onRegister()
    fun onNavigateToLogin()
    fun setUsername(value: String)
    fun setPassword(value: String)
    fun setConfirmPassword(value: String)
    fun validateRegisterFields(): Boolean
    fun setPhoneNumber(value: String)
    fun setAddress(value: String)

    object PreviewInstance: RegisterInteractionListener {
        override fun onRegister() {}
        override fun onNavigateToLogin() {}
        override fun setUsername(value: String) {}
        override fun setPassword(value: String) {}
        override fun setConfirmPassword(value: String) {}
        override fun validateRegisterFields(): Boolean = true
        override fun setPhoneNumber(value: String) {}
        override fun setAddress(value: String) {}
    }
}
