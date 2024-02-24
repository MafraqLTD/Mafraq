package com.mafraq.data.entities

import com.mafraq.data.entities.login.User


data class ApiResponse(
    val isSuccess: Boolean = false,
    val message: String? = null,
    val user: User? = null
)
