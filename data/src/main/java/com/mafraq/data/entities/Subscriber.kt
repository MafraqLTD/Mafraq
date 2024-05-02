package com.mafraq.data.entities

import com.mafraq.data.entities.map.Location
import com.mafraq.data.entities.profile.DayOff
import com.mafraq.data.remote.dataSource.subscription.employee.SubscribeRequestStatus


data class Subscriber(
    val email: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val homeLocation: Location = Location(),
    val workLocation: Location = Location(),
    val offDays: List<DayOff> = emptyList(),
    val phone: String = "",
    val active: Boolean = false,
    val status: SubscribeRequestStatus = SubscribeRequestStatus.Idle,
)
