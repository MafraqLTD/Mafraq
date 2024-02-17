package com.mafraq.presentation.utils.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun <E> E.Listen(onEvent: (currentEvent: E) -> Unit) {
    LaunchedEffect(key1 = this) {
        onEvent(this@Listen)
    }
}

@Composable
fun <E> E.Listen(filter: E.() -> Boolean = { true }, onEvent: (currentEvent: E) -> Unit) {
    LaunchedEffect(key1 = this) {
        if (filter())
            onEvent(this@Listen)
    }
}
