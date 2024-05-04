package com.mafraq.data.entities.profile

import com.mafraq.data.entities.map.Location


data class Employee(
    val rowId: Int = -1,
    val birthday: String = "",
    val offDays: List<DayOff> = emptyList(),
    val email: String = "",
    val id: String = "",
    val driverEmail: String = "",
    val fullName: String = "",
    val gender: String = "",
    val homeLocation: Location = Location(),
    val workLocation: Location = Location(),
    val phone: String = "",
    val profilePictureUrl: String = "",
    val subscriptionStatus: EmployeeSubscriptionStatus = EmployeeSubscriptionStatus.Inactive,
)
