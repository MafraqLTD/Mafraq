package com.mafraq.data.entities.profile

import com.mafraq.data.entities.map.Location


data class EmployeeProfile(
    val birthday: String,
    val offDays: Set<DayOff>,
    val email: String,
    val fullName: String,
    val gender: String,
    val homeLocation: Location,
    val workLocation: Location,
    val phone: String,
)
