package com.mafraq.data.remote.models

import com.google.gson.annotations.SerializedName


data class ApiResponseRemote<T>(
    @SerializedName("rows")
    private val rows: List<RowRemote<T>>?
) {
    data class RowRemote<T>(
        @SerializedName("row_id")
        val rowId: Int,
        @SerializedName("fields")
        val item: T,
    )

    val items: List<RowRemote<T>>
        get() = rows ?: emptyList()

    val item: RowRemote<T>?
        get() = items.firstOrNull()
}
