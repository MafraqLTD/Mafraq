package com.mafraq.presentation.design.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp


@Immutable
data class Shapes(
    val default: CornerBasedShape = RoundedCornerShape(0.dp),
    val extraSmall: CornerBasedShape = RoundedCornerShape(2.dp),
    val small: CornerBasedShape = RoundedCornerShape(4.dp),
    val medium: CornerBasedShape = RoundedCornerShape(8.dp),
    val large: CornerBasedShape = RoundedCornerShape(16.dp),
    val extraLarge: CornerBasedShape =  RoundedCornerShape(24.dp)
) {
    @Composable
    fun toM3Shapes() = MaterialTheme.shapes.copy(
        small = small,
        medium = medium,
        large = large,
        extraSmall = extraSmall,
        extraLarge = extraLarge
    )
}
