package com.mafraq.presentation.features.profile

import java.util.UUID

sealed class ProfileEvent(val id: Int) {
    data object OnLogout : ProfileEvent(id = 0)

    data class OnNavigateToMapForHomeAddress(
        private val uuid: UUID = UUID.randomUUID()
    ) : ProfileEvent(id = 1)

    data class OnNavigateToMapForWorkAddress(
        private val uuid: UUID = UUID.randomUUID()
    ) : ProfileEvent(id = 2)

    data object OnNavigateToHome : ProfileEvent(id = 3)
}
