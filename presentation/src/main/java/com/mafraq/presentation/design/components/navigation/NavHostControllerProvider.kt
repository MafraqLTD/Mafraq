package com.mafraq.presentation.design.components.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController


val LocalNavigationProvider =
    staticCompositionLocalOf<NavHostController> { error("No navigation host controller provided.") }

