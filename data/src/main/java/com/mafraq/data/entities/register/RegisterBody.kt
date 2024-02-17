package com.mafraq.data.entities.register


data class RegisterBody(
    val address: String,
    val phoneNumber: String,
    val username: String,
    val password: String,
    val confirmPassword: String,
)
