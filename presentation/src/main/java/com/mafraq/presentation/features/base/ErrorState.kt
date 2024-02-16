package com.mafraq.presentation.features.base

sealed class ErrorState(open val message: String? = null) {
    data class NoInternet(override val message: String? = null) : ErrorState(message)
    data class NoService(override val message: String? = null) : ErrorState(message)
    data class UnAuthorized(override val message: String? = null) : ErrorState(message)
    data class RequestFailed(override val message: String? = null) : ErrorState(message)
    data class Validation(override val message: String? = null) : ErrorState(message)
    data class EmptyBody(override val message: String? = null) : ErrorState(message)
    data class Timeout(override val message: String? = null) : ErrorState(message)
    data class GpsProviderIsDisabled(override val message: String? = null) : ErrorState(message)
}
