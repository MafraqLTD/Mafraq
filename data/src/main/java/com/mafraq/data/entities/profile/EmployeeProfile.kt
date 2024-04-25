package com.mafraq.data.entities.profile

import com.mafraq.data.entities.map.Location
import java.time.LocalDate


data class EmployeeProfile(
    val birthday: LocalDate = LocalDate.now(),
    val offDays: Set<DayOff> = setOf(),
    val email: String = "",
    val fullName: String = "",
    val gender: String = "",
    val homeLocation: Location = Location(),
    val workLocation: Location = Location(),
    val phone: String = "",
)
