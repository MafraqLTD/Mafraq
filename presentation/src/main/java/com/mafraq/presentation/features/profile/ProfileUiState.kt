package com.mafraq.presentation.features.profile

import com.mafraq.presentation.features.base.ErrorState
import com.mafraq.presentation.utils.extensions.emptyString

data class ProfileUiState(
    val email: String = emptyString(),
    val username: String = emptyString(),
    val address: String = emptyString(),
    val phone: String = emptyString(),
    val fullname: String = emptyString(),
    val isLoading: Boolean = false,
    val error: ErrorState? = null,
) {
    val isError: Boolean get() = error != null
}
