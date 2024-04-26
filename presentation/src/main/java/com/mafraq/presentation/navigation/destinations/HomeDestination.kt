package com.mafraq.presentation.navigation.destinations

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mafraq.presentation.features.home.HomeScreen
import com.mafraq.presentation.features.home.HomeViewModel
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.navigate
import com.mafraq.presentation.utils.extensions.sharedViewModel


internal fun NavController.navigateToHome() {
    navigate(screen = Screen.Home, popBackstack = true)
}

internal fun NavGraphBuilder.homeDestination(navController: NavController) {
    composable(Screen.Home.route) {
        HomeScreen(viewModel = hiltViewModel(), navController = navController)
    }
}
