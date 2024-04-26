package com.mafraq.data.remote.models


import com.google.gson.annotations.SerializedName

data class LocationRemote(
    @SerializedName("lat")
    val latitude: Double? = null,
    @SerializedName("long")
    val longitude: Double? = null,
    @SerializedName("road")
    val road: String? = null,
    @SerializedName("state")
    val governorate: String? = null,
    @SerializedName("district")
    val city: String? = null,
    @SerializedName("suburb")
    val district: String? = null
)
