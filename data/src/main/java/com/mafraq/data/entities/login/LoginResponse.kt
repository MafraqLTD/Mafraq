package com.mafraq.data.entities.login


data class LoginResponse(
    val user: User?,
    val accessToken: String?,
)
