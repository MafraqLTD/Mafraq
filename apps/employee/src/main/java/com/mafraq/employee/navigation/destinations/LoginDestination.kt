package com.mafraq.employee.navigation.destinations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mafraq.employee.features.auth.EmployeeAuthViewModel
import com.mafraq.presentation.features.authentication.ui.LoginScreen
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.navigate
import com.mafraq.presentation.utils.extensions.sharedViewModel


fun NavController.navigateToLogin() {
    navigate(screen = Screen.Login, popBackstack = true)
}

fun NavGraphBuilder.loginDestination(navController: NavController, navigateToHome: () -> Unit) {
    composable(Screen.Login.route) {
        val viewModel = it.sharedViewModel<EmployeeAuthViewModel>(navController = navController)
        LoginScreen(
            viewModel = viewModel,
            navController = navController,
            navigateToHome = navigateToHome,
            navigateToRegister = navController::navigateToRegister
        )
    }
}
