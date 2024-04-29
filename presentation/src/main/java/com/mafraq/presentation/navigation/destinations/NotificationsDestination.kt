package com.mafraq.presentation.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mafraq.presentation.features.notifications.NotificationsScreen
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.navigate


fun NavController.navigateToNotifications() {
    navigate(screen = Screen.Notifications, popBackstack = true)
}

fun NavGraphBuilder.notificationsDestination(navController: NavController) {
    composable(Screen.Notifications.route) {
        NotificationsScreen(viewModel = hiltViewModel(), navController = navController)
    }
}
