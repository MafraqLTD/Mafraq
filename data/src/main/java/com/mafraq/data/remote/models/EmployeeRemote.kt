package com.mafraq.data.remote.models

import com.google.gson.annotations.SerializedName
import com.mafraq.data.entities.SeparatedValuesListOfString
import java.util.UUID


data class EmployeeRemote(
    @SerializedName("Birthday")
    val birthday: String?,
    @SerializedName("Day off")
    val offDays: SeparatedValuesListOfString?,
    @SerializedName("Email")
    val email: String?,
    @SerializedName("Employee ID")
    val employeeID: String? = UUID.randomUUID().toString(),
    @SerializedName("Driver ID")
    val driverId: String?,
    @SerializedName("Full Name")
    val fullName: String?,
    @SerializedName("Gender")
    val gender: String?,
    @SerializedName("Home Location")
    val homeLocation: LocationRemote?,
    @SerializedName("Work Location")
    val workLocation: LocationRemote?,
    @SerializedName("Phone")
    val phone: String?,
    @SerializedName("Profile Picture")
    val profilePicture: String?,
    @SerializedName("Subscription Status")
    val subscriptionStatus: String?,
)
