package com.mafraq.data.remote.models.routes.response


import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

data class ViewportRemote(
    @SerializedName("high")
    val high: LatLng?,
    @SerializedName("low")
    val low: LatLng?
)