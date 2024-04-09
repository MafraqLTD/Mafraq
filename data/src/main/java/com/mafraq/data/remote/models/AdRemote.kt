package com.mafraq.data.remote.models

import com.google.gson.annotations.SerializedName


data class AdRemote(
    @SerializedName("Title")
    val title: String?,
    @SerializedName("Description")
    val description: String?,
    @SerializedName("Image")
    val imageUrl: String?,
)
