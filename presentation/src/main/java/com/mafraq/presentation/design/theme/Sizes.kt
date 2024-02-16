package com.mafraq.presentation.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Dimensions in Density-independent pixels (DP)
 * @property default The value is 0.dp.
 * @property extraSmall The value is 4.dp.
 * @property small The value is 8.dp.
 * @property medium The value is 16.dp.
 * @property large The value is 32.dp.
 * @property extraLarge The value is 64.dp.
 */
@Immutable
data class Sizes(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 64.dp,
)
