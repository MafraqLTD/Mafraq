package com.mafraq.driver.navigation.destinations

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mafraq.driver.home.HomeScreen
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.navigate


fun NavController.navigateToHome() {
    navigate(screen = Screen.Home, popBackstack = true)
}

fun NavGraphBuilder.homeDestination(navController: NavController) {
    composable(Screen.Home.route) {
        HomeScreen(
            viewModel = hiltViewModel(),
            navController = navController
        )
    }
}
