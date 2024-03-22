package com.mafraq.data.entities.chat


data class Message(
    val isFromMe: Boolean = false,
    val isRead: Boolean = false,
    val content: String = "",
    val receiveDate: String = "",
)
