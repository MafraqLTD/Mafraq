package com.mafraq.data.entities.login


data class LoginResponse(
    val user: AuthUser?,
    val accessToken: String?,
)
