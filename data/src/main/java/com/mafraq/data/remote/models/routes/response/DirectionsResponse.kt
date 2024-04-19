package com.mafraq.data.remote.models.routes.response


import com.google.gson.annotations.SerializedName

data class DirectionsResponse(
    @SerializedName("routes")
    val routes: List<RouteRemote>?
)