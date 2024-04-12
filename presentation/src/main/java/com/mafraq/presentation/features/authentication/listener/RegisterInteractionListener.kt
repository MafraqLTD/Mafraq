package com.mafraq.presentation.features.authentication.listener

import com.mafraq.presentation.utils.validation.ValidationState


interface RegisterInteractionListener : AuthInteractionListener {
    fun onRegister()
    fun onNavigateToLogin()
    fun setConfirmPassword(value: String)
    fun validateRegisterFields(): ValidationState

    object PreviewInstance: RegisterInteractionListener {
        override fun onRegister() {}
        override fun onNavigateToLogin() {}
        override fun setEmail(value: String) {}
        override fun setPassword(value: String) {}
        override fun setConfirmPassword(value: String) {}
        override fun validateRegisterFields(): ValidationState = ValidationState.Empty
    }
}
