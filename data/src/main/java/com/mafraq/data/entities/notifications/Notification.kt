package com.mafraq.data.entities.notifications


data class Notification(
    val id: String = "",
    val title: String = "",
    val body: String = "",
    val createdAt: String = "",
    var read: Boolean = false
)
