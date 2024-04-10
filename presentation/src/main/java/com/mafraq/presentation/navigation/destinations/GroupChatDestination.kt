package com.mafraq.presentation.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mafraq.presentation.features.chat.ChatScreen
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.navigate


internal fun NavController.navigateToGroupChat() {
    navigate(screen = Screen.GroupChat, popBackstack = true)
}

internal fun NavGraphBuilder.groupChatDestination(navController: NavController) {
    composable(Screen.GroupChat.route) {
        ChatScreen(viewModel = hiltViewModel(), navController = navController)
    }
}
