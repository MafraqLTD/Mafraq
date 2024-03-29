package com.mafraq.presentation.design.components.snackbar

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

private val snackState = SnackbarHostState()
val LocalSnackState = compositionLocalOf { snackState }
private var isSnackBarVisible: Boolean by mutableStateOf(false)

var SnackbarHostState.isVisible: Boolean
    get() = isSnackBarVisible
    set(value) {
        isSnackBarVisible = value
    }

val SnackbarHostState.isInvisible: Boolean
    get() = !isVisible
