package com.mafraq.presentation.design.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.ln


data class Colors(
    val primary: Color,
    val secondary: Color,
    val contentPrimary: Color,
    val contentSecondary: Color,
    val contentTertiary: Color,
    val contentBorder: Color,
    val surface: Color,
    val onPrimary: Color,
    val background: Color,
    val disable: Color,
    val divider:Color,
    val success: Color,
    val successContainer: Color,
    val warning: Color,
    val warningContainer: Color,
    val surfaceTint: Color,
){

    /**
     * Returns the new background [Color] to use, representing the original background [color] with an
     * overlay corresponding to [elevation] applied. The overlay will only be applied to
     * [ColorScheme.surface].
     */
    fun applyTonalElevation(backgroundColor: Color, elevation: Dp): Color =
        if (backgroundColor == surface)
            surfaceColorAtElevation(elevation)
        else
            backgroundColor

    /**
     * Computes the surface tonal color at different elevation levels e.g. surface1 through surface5.
     *
     * @param elevation Elevation value used to compute alpha of the color overlay layer.
     *
     * @return the [ColorScheme.surface] color with an alpha of the [ColorScheme.surfaceTint] color
     * overlaid on top of it.

     */
    fun surfaceColorAtElevation(
        elevation: Dp,
    ): Color {
        if (elevation == 0.dp) return surface
        val alpha = ((4.5f * ln(elevation.value + 1)) + 2f) / 100f
        return surfaceTint.copy(alpha = alpha).compositeOver(surface)
    }

    companion object{
        val LightColors = Colors(
            primary = Color(0xFF0B6464),
            secondary = Color(0xFFFFF3F2),
            contentPrimary = Color(0xDE1F0000),
            contentSecondary = Color(0x991F0000),
            contentTertiary = Color(0x611F0000),
            contentBorder = Color(0x141F0000),
            surface = Color(0xFFFFFFFF),
            onPrimary = Color(0xFFFFFFFF),
            background = Color(0xFFFAFAFA),
            disable = Color(0x401F0000),
            divider = Color(0x141F0000),
            success = Color(0xFF41BE88),
            successContainer = Color(0xFFF0FFF7),
            warning = Color(0xFFF2BD00),
            warningContainer = Color(0xFFFFFCEB),
            surfaceTint = Color(0x081F0000),
        )

        val DarkColors = Colors(
            primary = Color(0xFF0B6464),
            secondary = Color(0x1AFFFFFF),
            contentPrimary = Color(0xDEFFFFFF),
            contentSecondary = Color(0x99FFFFFF),
            contentTertiary = Color(0x61FFFFFF),
            contentBorder = Color(0x14FFEFEF),
            surface = Color(0xFF1C1C1C),
            onPrimary = Color(0xFFFFFFFF),
            background = Color(0xFF151515),
            disable = Color(0x40FFFFFF),
            divider = Color(0x14FFFFFF),
            success = Color(0xFF66CB9F),
            successContainer = Color(0x14EBFFF4),
            warning = Color(0xFFCBB567),
            warningContainer = Color(0x14FFFCEB),
            surfaceTint = Color(0x081F0000),
        )
    }
}
