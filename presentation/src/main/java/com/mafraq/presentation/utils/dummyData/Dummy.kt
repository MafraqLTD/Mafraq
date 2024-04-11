package com.mafraq.presentation.utils.dummyData

import com.mafraq.data.entities.notifications.Notification
import com.mafraq.presentation.utils.extensions.emptyString
import java.util.UUID


internal object Dummy {

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
