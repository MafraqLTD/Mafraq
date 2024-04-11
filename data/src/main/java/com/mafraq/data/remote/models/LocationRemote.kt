package com.mafraq.data.remote.models


import com.google.gson.annotations.SerializedName

data class LocationRemote(
    @SerializedName("country")
    val country: String?,
    @SerializedName("country_code")
    val countryCode: String?,
    @SerializedName("county")
    val county: String?,
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
    val state: String?,
    @SerializedName("town")
    val town: String?,
    @SerializedName("zoomLevel")
    val zoomLevel: Int?
)
