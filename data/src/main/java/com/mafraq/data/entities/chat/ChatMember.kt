package com.mafraq.data.entities.chat


data class ChatMember(
    val id: String = "",
    val name: String = "",
    private val active: Boolean = false
) {
    val isActive: Boolean
        get() = active
}
