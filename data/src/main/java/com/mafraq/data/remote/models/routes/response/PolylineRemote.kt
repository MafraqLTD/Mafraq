package com.mafraq.data.remote.models.routes.response


import com.google.gson.annotations.SerializedName

data class PolylineRemote(
    @SerializedName("encodedPolyline")
    val encodedPolyline: String?
)