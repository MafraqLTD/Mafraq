package com.mafraq.presentation.design.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Immutable
data class Typography(
    val headlineSmall: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W600,
    ),
    val headlineMedium: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.W600,
    ),
    val headlineLarge: TextStyle = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.4.sp,
        fontWeight = FontWeight.W600,
    ),
    val titleSmall: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W600,
    ),
    val titleMedium: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W600,
    ),
    val titleLarge: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.W600,
    ),
    val body: TextStyle = TextStyle(
        fontSize = 14.sp,
        lineHeight = 19.6.sp,
        fontWeight = FontWeight.W400,
    ),
    val label: TextStyle = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
    ),
) {

    @Composable
    fun toM3Typography() = MaterialTheme.typography.copy(
        headlineLarge = headlineLarge,
        headlineMedium = headlineMedium,
        headlineSmall = headlineSmall,
        titleLarge = titleLarge,
        titleMedium = titleMedium,
        titleSmall = titleSmall,
        bodyMedium = body,
        labelMedium = label,
    )
}
