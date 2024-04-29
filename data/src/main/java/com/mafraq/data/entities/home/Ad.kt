package com.mafraq.data.entities.home


data class Ad(
    val rowId: Int = -1,
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val isDriverAd: Boolean,
)
