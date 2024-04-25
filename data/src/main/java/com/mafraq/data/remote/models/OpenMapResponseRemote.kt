package com.mafraq.data.remote.models


import com.google.gson.annotations.SerializedName

data class OpenMapResponseRemote(
    @SerializedName("results")
    val results: List<ResultRemote?>?
)