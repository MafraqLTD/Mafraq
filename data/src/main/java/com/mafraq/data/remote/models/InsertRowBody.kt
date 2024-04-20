package com.mafraq.data.remote.models


import com.google.gson.annotations.SerializedName


data class InsertRowBody(
    @SerializedName("data")
    val value: List<Data>,
) {
    data class Data(
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

        private fun updateColumn(
            id: String,
            value: String,
        ): Column = Column(id = id, updatedValue = value)

        fun create(vararg column: Column): InsertRowBody =
            InsertRowBody(value = listOf(Data(row = column.toList())))

        fun updateFromMap(values: Map<String, String>): InsertRowBody {
            val columns = values.map { (key, value) -> updateColumn(id = key, value = value) }
            return InsertRowBody(value = listOf(Data(row = columns)))
        }

        fun createFromMap(values: Map<String, String>): InsertRowBody {
            val columns = values.map { (key, value) -> createColumn(id = key, value = value) }
            return InsertRowBody(value = listOf(Data(row = columns)))
        }
    }
}
