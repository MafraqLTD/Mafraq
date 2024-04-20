package com.mafraq.data.remote.models.routes.request


import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

data class RouteLocation(
    @SerializedName("latLng")
    val latLng: LatLng?
)