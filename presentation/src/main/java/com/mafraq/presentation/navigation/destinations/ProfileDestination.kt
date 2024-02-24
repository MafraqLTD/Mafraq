package com.mafraq.presentation.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mafraq.presentation.features.profile.ProfileScreen
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.navigate


internal fun NavController.navigateToProfile() {
    navigate(screen = Screen.Profile, popBackstack = true)
}

internal fun NavGraphBuilder.profileDestination(navController: NavController) {
    composable(Screen.Profile.route) {
        ProfileScreen(viewModel = hiltViewModel(), navController = navController)
    }
}
