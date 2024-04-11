package com.mafraq.data.entities.chat

import com.google.firebase.Timestamp
import java.util.UUID


data class Message(
    val id: String = generateRandomId(),
    val content: String = "",
    val senderName: String = "",
    val senderImageUrl: String = "",
    val isRead: Boolean = false,
    val isFromMe: Boolean = false,
    val timestamp: Timestamp = Timestamp.now(),
){
    companion object {
        fun generateRandomId() = UUID.randomUUID().toString().split('-').last()
    }
}
