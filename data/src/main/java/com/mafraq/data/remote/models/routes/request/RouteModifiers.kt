package com.mafraq.data.remote.models.routes.request


import com.google.gson.annotations.SerializedName

data class RouteModifiers(
    @SerializedName("avoidFerries")
    val avoidFerries: Boolean? = false,
    @SerializedName("avoidHighways")
    val avoidHighways: Boolean? = false,
    @SerializedName("avoidIndoor")
    val avoidIndoor: Boolean? = false,
    @SerializedName("avoidTolls")
    val avoidTolls: Boolean? = false
)