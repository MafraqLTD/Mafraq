package com.mafraq.presentation.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mafraq.presentation.features.map.MapScreen
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.navigation.Screen.Map.ADDRESS_ID_ARG
import com.mafraq.presentation.navigation.Screen.Map.IS_FROM_PROFILE_ARG
import com.mafraq.presentation.navigation.Screen.Map.LATITUDE_ARG
import com.mafraq.presentation.navigation.Screen.Map.LONGITUDE_ARG
import com.mafraq.presentation.utils.extensions.navigate


fun NavController.navigateToMap(
    latitude: Float = 0f,
    longitude: Float = 0f,
    addressId: Int = 0,
    fromProfile: Boolean = false,
) {
    navigate(screen = Screen.Map.withArgs(
        latitude,
        longitude,
        addressId,
        fromProfile
    ))
}

fun NavGraphBuilder.mapDestination(navController: NavController) {
    composable(
        route = Screen.Map.destination.route,
        arguments = listOf(
            navArgument(LATITUDE_ARG) { type = NavType.FloatType },
            navArgument(LONGITUDE_ARG) { type = NavType.FloatType },
            navArgument(ADDRESS_ID_ARG) { type = NavType.IntType },
            navArgument(IS_FROM_PROFILE_ARG) { type = NavType.BoolType },
        ),
    ) {
        MapScreen(
            viewModel = hiltViewModel(),
            navController = navController
        )
    }
}
