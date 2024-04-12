package com.mafraq.data.entities

import com.mafraq.data.entities.map.Location


data class Subscriber(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val location: Location = Location()
)
