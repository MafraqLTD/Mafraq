package com.mafraq.data.remote.models

import com.google.gson.annotations.SerializedName


data class ApiResponseDto(
    @SerializedName("rows")
    private val rows: List<RowDto>?
) {
    data class RowDto(
        @SerializedName("fields")
        val driver: DriverRemote,
    )

    val data: List<DriverRemote>
        get() = rows?.map { it.driver } ?: emptyList()

    val single: DriverRemote?
        get() = data.firstOrNull()
}
