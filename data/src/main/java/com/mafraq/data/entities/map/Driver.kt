package com.mafraq.data.entities.map

import com.mafraq.data.entities.Subscriber


data class Driver(
    val rowId: Int = -1,
    val birthday: String = "",
    val carName: String = "",
    val carNumber: String = "",
    val rating: String = "",
    val snippet: String = "",
    val email: String = "",
    val id: String = "",
    val nationalId: String = "",
    val gender: String = "",
    val location: Location = Location(),
    val fullName: String = "",
    val phone: String = "",
    val profilePictureUrl: String = "",
    val subscriptionStatus: DriverSubscriptionStatus = DriverSubscriptionStatus.Empty,
    val subscribers: List<Subscriber> = emptyList()
)
