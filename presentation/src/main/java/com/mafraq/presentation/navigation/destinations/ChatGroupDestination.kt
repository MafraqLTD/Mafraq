package com.mafraq.presentation.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mafraq.presentation.features.chat.group.ChatGroupScreen
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.navigate


fun NavController.navigateToChatGroup(popBackstack: Boolean = true) {
    navigate(screen = Screen.ChatGroup, popBackstack = popBackstack)
}

fun NavGraphBuilder.chatGroupDestination(navController: NavController) {
    composable(Screen.ChatGroup.route) {
        ChatGroupScreen(viewModel = hiltViewModel(), navController = navController)
    }
}
