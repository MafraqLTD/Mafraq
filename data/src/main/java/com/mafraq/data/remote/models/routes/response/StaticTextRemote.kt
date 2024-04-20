package com.mafraq.data.remote.models.routes.response


import com.google.gson.annotations.SerializedName

data class StaticTextRemote(
    @SerializedName("text")
    val text: String?
)