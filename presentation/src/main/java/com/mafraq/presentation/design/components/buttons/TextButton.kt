package com.mafraq.presentation.design.components.buttons

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TextButton(text: String, onClick: () -> Unit) {
    androidx.compose.material3.TextButton(onClick = onClick) {
        Text(text)
    }
}