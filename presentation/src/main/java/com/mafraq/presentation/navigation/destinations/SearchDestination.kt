package com.mafraq.presentation.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mafraq.presentation.features.chat.support.ChatSupportScreen
import com.mafraq.presentation.features.search.SearchScreen
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.navigate


internal fun NavController.navigateToSearch() {
    navigate(screen = Screen.Search, popBackstack = false)
}

internal fun NavGraphBuilder.searchDestination(navController: NavController) {
    composable(Screen.Search.route) {
        SearchScreen(viewModel = hiltViewModel(), navController = navController)
    }
}
