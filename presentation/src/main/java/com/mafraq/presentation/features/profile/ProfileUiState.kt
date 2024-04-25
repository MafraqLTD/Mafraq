package com.mafraq.presentation.features.profile

import com.mafraq.data.entities.map.Location
import com.mafraq.data.entities.profile.DayOff
import com.mafraq.data.entities.profile.EmployeeProfile
import com.mafraq.data.entities.profile.Gender
import com.mafraq.presentation.features.base.ErrorState
import com.mafraq.presentation.utils.extensions.emptyString

data class ProfileUiState(
    val email: String = emptyString(),
    val workLocation: Location = Location(),
    val homeLocation: Location = Location(),
    val phone: String = emptyString(),
    val fullName: String = emptyString(),
    val birthday: String = emptyString(),
    val offDays: Set<DayOff> = emptySet(),
    val gender: Gender? = null,
    val isLoading: Boolean = false,
    val error: ErrorState? = null,
) {
    val isError: Boolean get() = error != null

    fun toProfile() = EmployeeProfile(
        email = email,
        workLocation = workLocation,
        homeLocation = homeLocation,
        phone = phone,
        fullName = fullName,
        birthday = birthday,
        offDays = offDays,
        gender = gender?.name.orEmpty(),
    )

    companion object {
        fun empty() = ProfileUiState()
    }
}
