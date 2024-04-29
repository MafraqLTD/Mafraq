package com.mafraq.presentation.design.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.ln


@Immutable
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
    val divider: Color,
    val success: Color,
    val successContainer: Color,
    val warning: Color,
    val warningContainer: Color,
    val error: Color,
    val errorContainer: Color,
    val surfaceTint: Color,
) {

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

    companion object {
        val LightColors = Colors(
            primary = Color(0xFF0B6464),
            secondary = Color(0xFF4a6363),
            contentPrimary = Color(0xFF002020),
            contentSecondary = Color(0xFF051f1f),
            contentTertiary = Color(0xFF041c35),
            contentBorder = Color(0x141F0000),
            surface = Color(0xFFfafdfc),
            onPrimary = Color(0xFFFFFFFF),
            background = Color(0xFFFAFAFA),
            disable = Color(0x40051f1f),
            divider = Color(0x14041c35),
            success = Color(0xFF41BE88),
            successContainer = Color(0xFFF0FFF7),
            warning = Color(0xFFF2BD00),
            warningContainer = Color(0xFFFFFCEB),
            surfaceTint = Color(0x081F0000),
            error = Color(0xFFDC362E),
            errorContainer = Color(0xFFFF8A8A),
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
            error = Color(0xFFDC362E),
            errorContainer = Color(0xFFFF8A8A),
        )
    }

    @Composable
    fun toColorScheme() = MaterialTheme.colorScheme.copy(
        primary = primary,
        onPrimary = onPrimary,
        onPrimaryContainer = contentPrimary,
        secondary = secondary,
        onSecondaryContainer = contentSecondary,
        onTertiaryContainer = contentTertiary,
        background = background,
        surface = surface,
        surfaceTint = surfaceTint,
        primaryContainer = Color(0xFF6ff7f6),
        onSecondary = Color(0xFFFFFFFF),
        secondaryContainer = Color(0xFFcce8e7),
        tertiary = Color(0xFF4b607c),
        onTertiary = Color(0xFFFFFFFF),
        tertiaryContainer = Color(0xFFd3e4ff),
        onBackground = Color(0xFF191c1c),
        onSurface = Color(0xFF191C1C),
        surfaceVariant = Color(0xFFDAE5E4),
        onSurfaceVariant = Color(0xFF3F4948),
        outline = Color(0xFF6f7979),
        outlineVariant = divider,
        surfaceContainer = Color(0xFF6F7979),
    )
}
