package com.mafraq.presentation.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mafraq.presentation.features.chat.ChatScreen
import com.mafraq.presentation.features.chat.ChatViewModel
import com.mafraq.presentation.features.map.MapScreen
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.navigate
import com.mafraq.presentation.utils.extensions.sharedViewModel


internal fun NavController.navigateToMap() {
    navigate(screen = Screen.Map, popBackstack = false)
}

internal fun NavGraphBuilder.mapDestination(navController: NavController) {
    composable(Screen.Map.route) {
        MapScreen(viewModel = hiltViewModel(), navController = navController)
    }
}
