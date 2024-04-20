package com.mafraq.data.entities.profile

import com.mafraq.data.entities.map.Location


data class EmployeeProfile(
    val birthday: String = "",
    val offDays: Set<DayOff> = setOf(),
    val email: String = "",
    val fullName: String = "",
    val gender: String = "",
    val homeLocation: Location = Location(),
    val workLocation: Location = Location(),
    val phone: String = "",
)
