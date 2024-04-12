package com.mafraq.data.entities.chat

import com.google.firebase.Timestamp
import java.util.UUID


data class Message(
    val id: String = "",
    val content: String = "",
    val senderName: String = "",
    val senderImageUrl: String = "",
    val isRead: Boolean = false,
    val isFromMe: Boolean = false,
    val receivedAt: String = "",
)
