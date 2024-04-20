package com.mafraq.data.remote.models.routes.request


import com.google.gson.annotations.SerializedName

data class RouteBody(
    @SerializedName("location")
    val location: RouteLocation?,
    @SerializedName("sideOfRoad")
    val sideOfRoad: Boolean? = false,
    @SerializedName("vehicleStopover")
    val vehicleStopover: Boolean? = false
)