package com.mafraq.data.remote.models.chat

import com.google.firebase.Timestamp


data class MessageRemote(
    val id: String,
    val senderId: String = "",
    val content: String = "",
    val senderName: String = "",
    val senderImageUrl: String = "",
    val read: Boolean = false,
    val timestamp: Timestamp = Timestamp.now(),
)
