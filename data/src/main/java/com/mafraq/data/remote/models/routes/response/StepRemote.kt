package com.mafraq.data.remote.models.routes.response


import com.google.gson.annotations.SerializedName

data class StepRemote(
    @SerializedName("distanceMeters")
    val distanceMeters: Int?,
    @SerializedName("endLocation")
    val endLocation: LocationRemote?,
    @SerializedName("localizedValues")
    val localizedValues: LocalizedValuesRemote?,
    @SerializedName("navigationInstruction")
    val navigationInstruction: NavigationInstructionRemote?,
    @SerializedName("polyline")
    val polyline: PolylineRemote?,
    @SerializedName("startLocation")
    val startLocation: LocationRemote?,
    @SerializedName("staticDuration")
    val staticDuration: String?,
    @SerializedName("travelMode")
    val travelMode: String?,
    @SerializedName("steps")
    val steps: List<StepRemote>?,
)