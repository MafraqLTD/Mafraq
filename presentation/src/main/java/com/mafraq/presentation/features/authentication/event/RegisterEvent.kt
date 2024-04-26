package com.mafraq.presentation.features.authentication.event


sealed interface RegisterEvent : AuthEvent {
    data object OnRegister : RegisterEvent
    data object OnNavigateToLogin : RegisterEvent
    data object OnNavigateToLoginProfile : RegisterEvent
}
