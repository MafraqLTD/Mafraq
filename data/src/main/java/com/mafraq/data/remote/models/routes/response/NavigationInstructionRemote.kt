package com.mafraq.data.remote.models.routes.response


import com.google.gson.annotations.SerializedName

data class NavigationInstructionRemote(
    @SerializedName("instructions")
    val instructions: String?,
    @SerializedName("maneuver")
    val maneuver: String?
)