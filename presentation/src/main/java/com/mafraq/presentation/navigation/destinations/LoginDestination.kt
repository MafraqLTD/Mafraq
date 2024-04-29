package com.mafraq.presentation.navigation.destinations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mafraq.presentation.features.authentication.ui.LoginScreen
import com.mafraq.presentation.features.authentication.viewmodel.AuthViewModel
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.navigate
import com.mafraq.presentation.utils.extensions.sharedViewModel


fun NavController.navigateToLogin() {
    navigate(screen = Screen.Login, popBackstack = true)
}

fun NavGraphBuilder.loginDestination(navController: NavController) {
    composable(Screen.Login.route) {
        val viewModel = it.sharedViewModel<AuthViewModel>(navController = navController)
        LoginScreen(viewModel = viewModel, navController = navController)
    }
}
