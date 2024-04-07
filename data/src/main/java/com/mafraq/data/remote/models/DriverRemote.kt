package com.mafraq.data.remote.models

import com.google.gson.annotations.SerializedName


data class DriverRemote(
    @SerializedName("Birthday")
    val birthday: String?,
    @SerializedName("Email")
    val email: String?,
    @SerializedName("Driver ID")
    val id: String?,
    @SerializedName("Full Name")
    val fullName: String?,
    @SerializedName("Location")
    val location: LocationRemote?,
    @SerializedName("Phone")
    val phone: String?,
    @SerializedName("Profile Picture")
    val profilePicture: String?,
    @SerializedName("Subscription Status")
    val subscriptionStatus: String?,
    @SerializedName("Car")
    val car: String?,
    @SerializedName("Car Number")
    val carNumber: String?,
    @SerializedName("Rating")
    val rating: String?,
    @SerializedName("Snippet")
    val snippet: String?
)
