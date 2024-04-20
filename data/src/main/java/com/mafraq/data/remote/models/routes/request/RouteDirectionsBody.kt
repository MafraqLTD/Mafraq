package com.mafraq.data.remote.models.routes.request


import com.google.gson.annotations.SerializedName

data class RouteDirectionsBody(
    @SerializedName("origin")
    val origin: RouteBody?,
    @SerializedName("destination")
    val destination: RouteBody?,
    @SerializedName("computeAlternativeRoutes")
    val computeAlternativeRoutes: Boolean? = false,
    @SerializedName("routeModifiers")
    val modifiers: RouteModifiers? = RouteModifiers(),
    @SerializedName("polylineQuality")
    val polylineQuality: String? = "high_quality",
    @SerializedName("routingPreference")
    val routingPreference: String? = "traffic_unaware",
    @SerializedName("travelMode")
    val travelMode: String? = "drive"
)