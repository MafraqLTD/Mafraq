package com.mafraq.presentation.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mafraq.presentation.features.chat.support.ChatSupportScreen
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.navigate


internal fun NavController.navigateToChatSupport() {
    navigate(screen = Screen.ChatSupport, popBackstack = false)
}

internal fun NavGraphBuilder.chatSupportDestination(navController: NavController) {
    composable(Screen.ChatSupport.route) {
        ChatSupportScreen(viewModel = hiltViewModel(), navController = navController)
    }
}
