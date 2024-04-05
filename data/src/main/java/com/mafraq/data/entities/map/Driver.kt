package com.mafraq.data.entities.map


data class Driver(
    val name: String = "",
    val snippet: String = "",
    val profilePic: String = "",
    val rating: String = "",
    val car: String = "",
    val carNumber: String = "",
    val location: Location = Location()
)
