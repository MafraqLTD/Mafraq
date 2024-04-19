package com.mafraq.presentation.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mafraq.presentation.features.map.MapScreen
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.navigation.Screen.Map.LATITUDE_ARG
import com.mafraq.presentation.navigation.Screen.Map.LONGITUDE_ARG
import com.mafraq.presentation.utils.extensions.navigate


internal fun NavController.navigateToMap(latitude: Float, longitude: Float) {
    navigate(screen = Screen.Map.withArgs(latitude, longitude), popBackstack = false)
}

internal fun NavGraphBuilder.mapDestination(navController: NavController) {
    composable(
        route = Screen.Map.destination.route,
        arguments = listOf(
            navArgument(LATITUDE_ARG) { type = NavType.FloatType },
            navArgument(LONGITUDE_ARG) { type = NavType.FloatType },
        ),
    ) {
        MapScreen(viewModel = hiltViewModel(), navController = navController)
    }
}
