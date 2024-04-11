package com.mafraq.presentation.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mafraq.presentation.features.chat.support.ChatScreen
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.navigate


internal fun NavController.navigateToChatGroup() {
    navigate(screen = Screen.ChatGroup, popBackstack = true)
}

internal fun NavGraphBuilder.chatGroupDestination(navController: NavController) {
    composable(Screen.ChatGroup.route) {
        ChatScreen(viewModel = hiltViewModel(), navController = navController)
    }
}
