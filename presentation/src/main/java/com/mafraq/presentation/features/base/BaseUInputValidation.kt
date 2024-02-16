package com.mafraq.presentation.features.base

import com.mafraq.presentation.utils.validation.ValidationResult


abstract class BaseUInputValidation {
    abstract val attributes: List<ValidationResult>

    val isAllValid: Boolean
        get() = attributes.all { it.errorState.not() }
}
