package com.mafraq.presentation.navigation.destinations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mafraq.presentation.features.authentication.ui.RegisterScreen
import com.mafraq.presentation.features.authentication.viewmodel.AuthViewModel
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.navigate
import com.mafraq.presentation.utils.extensions.sharedViewModel


internal fun NavController.navigateToRegister() {
    navigate(screen = Screen.Register, popBackstack = true)
}

internal fun NavGraphBuilder.registerDestination(navController: NavController) {
    composable(Screen.Register.route) {
        val viewModel = it.sharedViewModel<AuthViewModel>(navController = navController)
        RegisterScreen(viewModel = viewModel, navController = navController)
    }
}
