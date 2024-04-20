package com.mafraq.presentation.features.profile

import com.mafraq.data.entities.profile.DayOff
import com.mafraq.data.entities.profile.Gender
import com.mafraq.presentation.features.base.ErrorState
import com.mafraq.presentation.utils.extensions.emptyString

data class ProfileUiState(
    val email: String = emptyString(),
    val workAddress: String = emptyString(),
    val homeAddress: String = emptyString(),
    val phone: String = emptyString(),
    val fullname: String = emptyString(),
    val birthday: String = emptyString(),
    val offDays: Set<DayOff> = emptySet(),
    val gender: Gender? = null,
    val isLoading: Boolean = false,
    val error: ErrorState? = null,
) {
    val isError: Boolean get() = error != null
}
