package com.mafraq.data.remote.models.chat

import com.google.firebase.Timestamp
import java.util.UUID


data class MessageRemote(
    val id: String = generateRandomId(),
    val senderId: String = "",
    val content: String = "",
    val senderName: String = "",
    val senderImageUrl: String = "",
    val read: Boolean = false,
    val timestamp: Timestamp = Timestamp.now(),
) {

    companion object {
        fun generateRandomId() = UUID.randomUUID().toString().split('-').last()
    }
}
