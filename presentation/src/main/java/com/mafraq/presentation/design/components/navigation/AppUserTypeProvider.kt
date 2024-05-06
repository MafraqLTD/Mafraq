package com.mafraq.presentation.design.components.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import com.mafraq.data.entities.AppUserType


val LocalAppUserType =
    staticCompositionLocalOf<AppUserType> { error("No navigation host controller provided.") }
