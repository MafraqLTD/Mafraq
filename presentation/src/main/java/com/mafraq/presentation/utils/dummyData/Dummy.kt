package com.mafraq.presentation.utils.dummyData

import com.mafraq.data.entities.chat.Message
import com.mafraq.data.entities.notifications.Notification
import com.mafraq.presentation.features.chatSupport.ChatSupportUiState
import com.mafraq.presentation.features.home.components.AdModel
import com.mafraq.presentation.utils.extensions.emptyString
import java.util.UUID

internal object Dummy {
    val dummyAds = listOf(
        AdModel(
            title = "Test1",
            description = "is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
            imageUrl = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg"
        ),
        AdModel(
            title = "Test2",
            description = "readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look",
            imageUrl = "https://cdn.pixabay.com/photo/2016/05/05/02/37/sunset-1373171_1280.jpg"
        ),
        AdModel(
            title = "اختبار",
            description = "وفي لم نهاية الربيع، وانتهاءً. و جدول الإكتفاء تحت. تم هذه بوابة مدينة شواطيء. عرض وصغار وإيطالي لم, عل به، يرتبط وايرلندا. خلاف أملاً عدم عل, بحث مع دخول بقعة وبعد.\n",
            imageUrl = "https://cdn.pixabay.com/photo/2016/09/29/14/24/landscape-1702945_960_720.jpg"
        ),
    )

    val dummyMessages = listOf(
        Message(
            isFromMe = false,
            isRead = true,
            content = "This is other test message",
            receiveDate = "2:11 PM"
        ),
        Message(
            isFromMe = true,
            isRead = true,
            content = "This is test message",
            receiveDate = "2:12 PM"
        ),
        Message(
            isFromMe = false,
            isRead = false,
            content = "This is other test message",
            receiveDate = "2:13 PM"
        ),
        Message(
            isFromMe = true,
            isRead = false,
            content = "This is test message",
            receiveDate = "2:14 PM"
        ),
        Message(
            isFromMe = false,
            isRead = false,
            content = "This is other test message",
            receiveDate = "2:15 PM"
        ),
        Message(
            isFromMe = true,
            isRead = false,
            content = "This is test message",
            receiveDate = "2:16 PM"
        ),
        Message(
            isFromMe = true,
            isRead = false,
            content = "This is test message",
            receiveDate = "2:17 PM"
        ),
        Message(
            isFromMe = false,
            isRead = false,
            content = "This is other test message",
            receiveDate = "2:18 PM"
        ),
        Message(
            isFromMe = true,
            isRead = false,
            content = "This is test message",
            receiveDate = "2:19 PM"
        ),
        Message(
            isFromMe = false,
            isRead = false,
            content = "This is other test message",
            receiveDate = "2:20 PM"
        ),
        Message(
            isFromMe = false,
            isRead = false,
            content = "This is other test message",
            receiveDate = "2:21 PM"
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
