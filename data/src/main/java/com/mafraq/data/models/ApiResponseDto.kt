package com.mafraq.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ApiResponseDto(
    @SerialName("success")
    val isSuccess: Boolean = false,

    @SerialName("message")
    val message: String? = null,
)
