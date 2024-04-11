package com.mafraq.data.remote.errors


data class FetchException(override val message: String?) : Exception(message)
