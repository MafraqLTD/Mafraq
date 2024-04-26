package com.mafraq.data.remote.models.chat

import com.google.firebase.Timestamp
import com.mafraq.data.utils.generateRandomId


data class MessageRemote(
    val id: String = String.generateRandomId(),
    val senderId: String = "",
    val content: String = "",
    val senderName: String = "",
    val senderImageUrl: String = "",
    val read: Boolean = false,
    val timestamp: Timestamp = Timestamp.now(),
)
