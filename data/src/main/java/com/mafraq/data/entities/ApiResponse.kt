package com.mafraq.data.entities

import com.mafraq.data.entities.map.Driver


data class ApiResponse(
    val data: List<Driver>? = null,
    val single: Driver? = null,
)
