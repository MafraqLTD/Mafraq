package com.mafraq.data.remote.models


import com.google.gson.annotations.SerializedName


data class InsertRowBody(
    @SerializedName("data")
    val value: List<Data>,
) {
    data class Data(
        @SerializedName("row_id")
        val rowId: Int? = null,
        @SerializedName("columns")
        val row: List<Column>
    )

    data class Column(
        @SerializedName("column_id")
        val id: String,
        @SerializedName("cell_value")
        val newValue: String? = null,
        @SerializedName("update_cell_value")
        val updatedValue: String? = null,
    )

    companion object {
        private fun createColumn(
            id: String,
            value: String,
        ): Column  = Column(id = id, newValue = value)

        fun create(vararg column: Column): InsertRowBody =
            InsertRowBody(value = listOf(Data(row = column.toList())))

        fun createFromMap(values: Map<String, String>): InsertRowBody {
            val columns = values.map { (key, value) -> createColumn(id = key, value = value) }
            return InsertRowBody(value = listOf(Data(row = columns)))
        }
    }
}
