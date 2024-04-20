package com.mafraq.data.remote.models.routes.response


import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

data class LocationRemote(
    @SerializedName("latLng")
    val latLng: LatLng?
)