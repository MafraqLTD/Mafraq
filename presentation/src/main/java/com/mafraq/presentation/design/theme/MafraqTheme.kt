package com.mafraq.presentation.design.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


@Stable
object MafraqTheme {

    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val sizes: Sizes
        @Composable
        @ReadOnlyComposable
        get() = LocalSizes.current

    @Composable
    operator fun invoke(
        useDarkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable MafraqTheme.() -> Unit
    ) {
        val colors = if (!useDarkTheme)
            Colors.LightColors
        else
            Colors.DarkColors

        val darkIcons: Boolean = !useDarkTheme
        val view = LocalView.current

        if (!view.isInEditMode)
            SideEffect {
                val window = (view.context as Activity).window
                window.statusBarColor = colors.background.toArgb()
                window.navigationBarColor =
                    colors.surfaceColorAtElevation(NavigationBarDefaults.Elevation).toArgb()

                val windowController = WindowCompat.getInsetsController(window, view)
                windowController.isAppearanceLightStatusBars = darkIcons
                windowController.isAppearanceLightNavigationBars = darkIcons
            }

        CompositionLocalProvider(
            LocalColors provides colors,
            LocalTypography provides Typography(),
            LocalSizes provides Sizes(),
            LocalShapes provides Shapes(),
        ) {
            MaterialTheme(
                shapes = shapes.toM3Shapes(),
                colorScheme = colors.toColorScheme(),
                typography = typography.toM3Typography()
            ) { content(this) }
        }
    }

    private val LocalSizes = compositionLocalOf { Sizes() }
    private val LocalShapes = compositionLocalOf { Shapes() }
    private val LocalTypography = compositionLocalOf { Typography() }
    private val LocalColors = compositionLocalOf { Colors.LightColors }
}
