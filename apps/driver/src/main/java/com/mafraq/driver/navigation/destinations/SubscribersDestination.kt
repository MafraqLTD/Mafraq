package com.mafraq.driver.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mafraq.driver.features.subscribers.SubscribersScreen
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.navigate


fun NavController.navigateToSubscribers() {
    navigate(screen = Screen.Subscribers, popBackstack = false)
}

fun NavGraphBuilder.subscribersDestination(navController: NavController) {
    composable(Screen.Subscribers.route) {
        SubscribersScreen(viewModel = hiltViewModel(), navController = navController)
    }
}
