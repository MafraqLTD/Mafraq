package com.mafraq.data.remote.models.routes.response


import com.google.gson.annotations.SerializedName

data class LocalizedValuesRemote(
    @SerializedName("distance")
    val distance: StaticTextRemote?,
    @SerializedName("duration")
    val duration: StaticTextRemote?,
    @SerializedName("staticDuration")
    val staticDuration: StaticTextRemote?
)