package com.mafraq.presentation.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mafraq.presentation.features.profile.ProfileScreen
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.navigation.Screen.Map.ADDRESS_ID_ARG
import com.mafraq.presentation.navigation.Screen.Map.LATITUDE_ARG
import com.mafraq.presentation.navigation.Screen.Map.LONGITUDE_ARG
import com.mafraq.presentation.utils.extensions.navigate


fun NavController.navigateToProfile(
    latitude: Float,
    longitude: Float,
    addressId: Int,
) {
    navigate(screen = Screen.Profile.withArgs(latitude, longitude, addressId), popBackstack = true)
}

fun NavController.navigateToProfile() {
    navigate(screen = Screen.Profile, popBackstack = true)
}

fun NavController.navigateToLoginProfile() {
    navigate(screen = Screen.LoginProfile, popBackstack = true)
}

fun NavGraphBuilder.profileDestination(navController: NavController) {
    composable(
        route = Screen.Profile.destination.route,
        arguments = listOf(
            navArgument(LATITUDE_ARG) { type = NavType.FloatType },
            navArgument(LONGITUDE_ARG) { type = NavType.FloatType },
            navArgument(ADDRESS_ID_ARG) { type = NavType.IntType },
        ),
    ) {
        ProfileScreen(viewModel = hiltViewModel(), navController = navController)
    }

    composable(route = Screen.Profile.route) {
        ProfileScreen(viewModel = hiltViewModel(), navController = navController)
    }

    composable(route = Screen.LoginProfile.route) {
        ProfileScreen(viewModel = hiltViewModel(), navController = navController)
    }
}
