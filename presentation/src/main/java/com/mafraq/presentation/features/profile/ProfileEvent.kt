package com.mafraq.presentation.features.profile

sealed interface ProfileEvent {
    data object OnLogout : ProfileEvent
}
