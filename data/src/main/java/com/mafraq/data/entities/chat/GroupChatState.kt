package com.mafraq.data.entities.chat


data class GroupChatState(
    val title: String = "",
    val members: Int = 0,
    val activeMembers: Int = 0
)
