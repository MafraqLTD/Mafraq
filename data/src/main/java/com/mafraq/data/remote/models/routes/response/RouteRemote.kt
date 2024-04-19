package com.mafraq.data.remote.models.routes.response


import com.google.gson.annotations.SerializedName

data class RouteRemote(
    @SerializedName("description")
    val description: String?,
    @SerializedName("distanceMeters")
    val distanceMeters: Int?,
    @SerializedName("duration")
    val duration: String?,
    @SerializedName("legs")
    val legs: List<StepRemote>?,
    @SerializedName("localizedValues")
    val localizedValues: LocalizedValuesRemote?,
    @SerializedName("polyline")
    val polyline: PolylineRemote?,
    @SerializedName("routeLabels")
    val routeLabels: List<String>?,
    @SerializedName("staticDuration")
    val staticDuration: String?,
    @SerializedName("viewport")
    val viewport: ViewportRemote?
)