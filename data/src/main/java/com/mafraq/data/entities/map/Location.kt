package com.mafraq.data.entities.map


data class Location(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val governorate: String = "",
    val city: String = "",
    val district: String = "",
    ) {
    val formattedAddress: String
        get() = "$governorate, $city, $district"
            .replace(", ,", "")
}
