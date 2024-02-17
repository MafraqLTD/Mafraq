package com.mafraq.presentation.features.authentication.event


sealed interface LoginEvent : AuthEvent {
    data object OnLogin : LoginEvent
    data object OnNavigateToRegister : LoginEvent
}
