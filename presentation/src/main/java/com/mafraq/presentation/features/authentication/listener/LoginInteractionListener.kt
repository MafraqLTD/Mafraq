package com.mafraq.presentation.features.authentication.listener

import com.mafraq.presentation.utils.validation.ValidationState


interface LoginInteractionListener : AuthInteractionListener {
    fun onLogin()
    fun onNavigateToRegister()
    fun validateLoginFields(): ValidationState

    object PreviewInstance: LoginInteractionListener {
        override fun onLogin() {}
        override fun onNavigateToRegister() {}
        override fun setEmail(value: String) {}
        override fun setPassword(value: String) {}
        override fun validateLoginFields(): ValidationState = ValidationState.Empty
    }
}
