package com.mafraq.data.remote.errors


data class InsertException(override val message: String?) : Exception(message)
