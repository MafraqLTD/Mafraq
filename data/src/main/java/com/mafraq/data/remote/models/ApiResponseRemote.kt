package com.mafraq.data.remote.models

import com.google.gson.annotations.SerializedName


data class ApiResponseRemote<T>(
    @SerializedName("rows")
    private val rows: List<RowRemote<T>>?
) {
    data class RowRemote<T>(
        @SerializedName("fields")
        val item: T,
    )

    val items: List<T>
        get() = rows?.map { it.item } ?: emptyList()

    val item: T?
        get() = items.firstOrNull()
}
