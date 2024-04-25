package com.mafraq.data.remote.models


import com.google.gson.annotations.SerializedName

data class ResultRemote(
    @SerializedName("address_line1")
    val addressLine1: String?,
    @SerializedName("address_line2")
    val addressLine2: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("country_code")
    val countryCode: String?,
    @SerializedName("county")
    val county: String?,
    @SerializedName("distance")
    val distance: Double?,
    @SerializedName("district")
    val district: String?,
    @SerializedName("formatted")
    val formatted: String?,
    @SerializedName("housenumber")
    val housenumber: String?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("plus_code")
    val plusCode: String?,
    @SerializedName("plus_code_short")
    val plusCodeShort: String?,
    @SerializedName("postcode")
    val postcode: String?,
    @SerializedName("result_type")
    val resultType: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("street")
    val street: String?,
    @SerializedName("suburb")
    val suburb: String?
)