package com.mafraq.data.entities.register


data class RegisterBody(
    val email: String,
    val password: String,
    val confirmPassword: String,
)
