package com.mafraq.data.remote.errors


data class DeleteException(override val message: String?) : Exception(message)
