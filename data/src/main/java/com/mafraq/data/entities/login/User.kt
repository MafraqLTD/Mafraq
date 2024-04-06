package com.mafraq.data.entities.login


data class User(
    val id: String? = null,
    val name: String? = null,
    val email: String? = null,
    val photoUrl: String? = null,
    val phoneNumber: String? = null,
    val isEmailVerified: Boolean? = null,
)
