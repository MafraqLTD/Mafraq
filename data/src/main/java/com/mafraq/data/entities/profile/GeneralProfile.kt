package com.mafraq.data.entities.profile

import com.mafraq.data.entities.map.Location
import java.time.LocalDate


data class GeneralProfile(
    val rowId: Int = -1,
    val birthday: LocalDate? = null,
    val offDays: Set<DayOff> = setOf(),
    val email: String = "",
    val fullName: String = "",
    val gender: String = "",
    val car: String = "",
    val carNumber: String = "",
    val nationalId: String = "",
    val snippet: String = "",
    val profilePictureUrl: String = "",
    val homeLocation: Location = Location(),
    val workLocation: Location = Location(),
    val phone: String = "",
)
