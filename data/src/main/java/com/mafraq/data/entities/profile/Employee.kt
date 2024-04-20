package com.mafraq.data.entities.profile

import com.mafraq.data.entities.map.Location


data class Employee(
    val birthday: String = "",
    val offDays: List<DayOff> = emptyList(),
    val email: String = "",
    val id: String = "",
    val driverId: String = "",
    val fullName: String = "",
    val gender: String = "",
    val homeLocation: Location = Location(),
    val workLocation: Location = Location(),
    val phone: String = "",
    val profilePicture: String = "",
    val subscriptionStatus: EmployeeSubscriptionStatus = EmployeeSubscriptionStatus.Inactive,
)
