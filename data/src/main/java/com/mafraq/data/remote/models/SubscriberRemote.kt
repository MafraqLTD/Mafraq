package com.mafraq.data.remote.models

import com.mafraq.data.entities.map.Location
import com.mafraq.data.entities.profile.DayOff


data class SubscriberRemote(
    val email: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val homeLocation: Location = Location(),
    val workLocation: Location = Location(),
    val offDays: List<String> = emptyList(),
    val phone: String = "",
    val status: String = "",
    val active: Boolean = false
)
