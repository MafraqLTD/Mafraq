package com.mafraq.employee.navigation.destinations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mafraq.employee.features.auth.EmployeeAuthViewModel
import com.mafraq.presentation.features.authentication.ui.RegisterScreen
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.navigate
import com.mafraq.presentation.utils.extensions.sharedViewModel


fun NavController.navigateToRegister() {
    navigate(screen = Screen.Register, popBackstack = true)
}

fun NavGraphBuilder.registerDestination(navController: NavController, navigateToHome: () -> Unit) {
    composable(Screen.Register.route) {
        val viewModel = it.sharedViewModel<EmployeeAuthViewModel>(navController = navController)
        RegisterScreen(
            viewModel = viewModel,
            navController = navController,
            navigateToHome = navigateToHome,
            navigateToLogin = navController::navigateToLogin
        )
    }
}
