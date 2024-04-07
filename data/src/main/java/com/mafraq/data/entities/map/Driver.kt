package com.mafraq.data.entities.map


data class Driver(
    val birthday: String = "",
    val car: String = "",
    val carNumber: String = "",
    val rating: String = "",
    val snippet: String = "",
    val email: String = "",
    val id: String = "",
    val location: Location = Location(),
    val fullName: String = "",
    val phone: String = "",
    val profilePicture: String = "",
    val subscriptionStatus: String = ""
)
