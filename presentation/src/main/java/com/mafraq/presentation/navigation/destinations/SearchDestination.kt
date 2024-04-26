package com.mafraq.presentation.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mafraq.presentation.features.chat.support.ChatSupportScreen
import com.mafraq.presentation.features.search.SearchScreen
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.navigation.Screen.Map.ADDRESS_ID_ARG
import com.mafraq.presentation.navigation.Screen.Map.IS_FROM_PROFILE_ARG
import com.mafraq.presentation.navigation.Screen.Map.LATITUDE_ARG
import com.mafraq.presentation.navigation.Screen.Map.LONGITUDE_ARG
import com.mafraq.presentation.utils.extensions.navigate


internal fun NavController.navigateToSearch(fromProfile: Boolean = false) {
    navigate(screen = Screen.Search.withArgs(fromProfile), popBackstack = false)
}

internal fun NavGraphBuilder.searchDestination(navController: NavController) {
    composable(
        route = Screen.Search.destination.route,
        arguments = listOf(
            navArgument(IS_FROM_PROFILE_ARG) { type = NavType.BoolType },
        )
    ) {
        SearchScreen(viewModel = hiltViewModel(), navController = navController)
    }
}
