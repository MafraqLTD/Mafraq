package com.mafraq.data.remote.models


import com.google.gson.annotations.SerializedName

data class LocationRemote(
    @SerializedName("country")
    val country: String?,
    @SerializedName("country_code")
    val countryCode: String?,
    @SerializedName("house_number")
    val houseNumber: Int?,
    @SerializedName("ISO3")
    val iso3: String?,
    @SerializedName("lat")
    val latitude: Double?,
    @SerializedName("lng")
    val longitude: Double?,
    @SerializedName("postcode")
    val postcode: String?,
    @SerializedName("road")
    val road: String?,
    @SerializedName("state")
    val governorate: String?,
    @SerializedName("district")
    val city: String?,
    @SerializedName("suburb")
    val district: String?,
    @SerializedName("zoomLevel")
    val zoomLevel: Int?
)
