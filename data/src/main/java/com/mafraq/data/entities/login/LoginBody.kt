package com.mafraq.data.entities.login


data class LoginBody(
    val username: String,
    val password: String,
    val rememberMe: Boolean = true,
)
