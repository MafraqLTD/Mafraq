package com.mafraq.data.remote.models

import com.google.gson.annotations.SerializedName


data class AdRemote(
    @SerializedName("Ad ID")
    val id: String?,
    @SerializedName("Title")
    val title: String?,
    @SerializedName("Description")
    val description: String?,
    @SerializedName("Image")
    val imageUrl: String?,
    @SerializedName("Is Driver Ad")
    val isDriverAd: String
)
