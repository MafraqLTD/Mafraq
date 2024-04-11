package com.mafraq.presentation.utils.dummyData

import com.mafraq.data.entities.chat.Message
import com.mafraq.data.entities.notifications.Notification
import com.mafraq.presentation.features.chat.support.ChatSupportUiState
import com.mafraq.presentation.utils.extensions.emptyString
import java.util.UUID


internal object Dummy {
    val dummyMessages = listOf(
        Message(
            isFromMe = false,
            isRead = true,
            content = "This is other test message",
            timestamp = "2:11 PM"
        ),
        Message(
            isFromMe = true,
            isRead = true,
            content = "This is test message",
            timestamp = "2:12 PM"
        ),
        Message(
            isFromMe = false,
            isRead = false,
            content = "This is other test message",
            timestamp = "2:13 PM"
        ),
        Message(
            isFromMe = true,
            isRead = false,
            content = "This is test message",
            timestamp = "2:14 PM"
        ),
        Message(
            isFromMe = false,
            isRead = false,
            content = "This is other test message",
            timestamp = "2:15 PM"
        ),
        Message(
            isFromMe = true,
            isRead = false,
            content = "This is test message",
            timestamp = "2:16 PM"
        ),
        Message(
            isFromMe = true,
            isRead = false,
            content = "This is test message",
            timestamp = "2:17 PM"
        ),
        Message(
            isFromMe = false,
            isRead = false,
            content = "This is other test message",
            timestamp = "2:18 PM"
        ),
        Message(
            isFromMe = true,
            isRead = false,
            content = "This is test message",
            timestamp = "2:19 PM"
        ),
        Message(
            isFromMe = false,
            isRead = false,
            content = "This is other test message",
            timestamp = "2:20 PM"
        ),
        Message(
            isFromMe = false,
            isRead = false,
            content = "This is other test message",
            timestamp = "2:21 PM"
        ),
    )

    object DummyChatUiState {
        val activeState = ChatSupportUiState(
            title = "Support",
            isUserActive = true,
            messages = dummyMessages
        )

        val inactiveState = ChatSupportUiState(
            title = "Support",
            isUserActive = false,
            messages = dummyMessages
        )
    }

    val dummyNotifications = listOf(
        Notification(
            id = UUID.randomUUID().toString(),
            title = "Driver arrive about 5min be ready!",
            body = emptyString(),
            createdAt = "11:37 AM",
            read = false
        ),
        Notification(
            id = UUID.randomUUID().toString(),
            title ="Driver has been changed",
            body = "Name: Ahmed Mones\n" +
                    "Car: Toyota Rav4 2021\n" +
                    "Color: Magnetic Black",
            createdAt = "11:20 AM",
            read = true
        ),
        Notification(
            id = UUID.randomUUID().toString(),
            title = "Driver arrive about 5min be ready!",
            body = emptyString(),
            createdAt = "11:16 AM",
            read = false
        ),
        Notification(
            id = UUID.randomUUID().toString(),
            title = "This is test notification",
            body = "We have nothing to say :)",
            createdAt = "10:13 PM",
            read = false
        ),
        Notification(
            id = UUID.randomUUID().toString(),
            title = "Driver has been changed",
            body ="Name: Mostafa Mohamed\n" +
                    "Car: Bogati Shiron\n" +
                    "Color: Magnetic Black",
            createdAt = "9:14 PM",
            read = true
        ),
    )
}
