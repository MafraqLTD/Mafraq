package com.mafraq.presentation.utils.validation


enum class ValidationState {
    Valid,
    Invalid,
    Empty;

    val isValid: Boolean
        get() = this == Valid

    val isInvalid: Boolean
        get() = this == Invalid

    val isEmpty: Boolean
        get() = this == Empty
}
