package com.mafraq.data.entities.chat

import com.mafraq.data.utils.generateRandomId


data class Message(
    val id: String = String.generateRandomId(),
    val content: String = "",
    val senderName: String = "",
    val senderImageUrl: String = "",
    val isRead: Boolean = false,
    val isFromMe: Boolean = true,
    val receivedAt: String = "",
)
