package com.mafraq.data.remote.models


import com.google.gson.annotations.SerializedName


data class UpdateRowBody(
    @SerializedName("rows")
    val value: List<InsertRowBody.Data>,
) {
    companion object {
        private fun updateColumn(
            id: String,
            value: String,
        ): InsertRowBody.Column = InsertRowBody.Column(id = id, updatedValue = value)

        fun updateFromMap(values: Map<String, String>, rowId: Int): UpdateRowBody {
            val columns = values.map { (key, value) -> updateColumn(id = key, value = value) }
            return UpdateRowBody(value = listOf(InsertRowBody.Data(row = columns, rowId = rowId)))
        }
    }
}
