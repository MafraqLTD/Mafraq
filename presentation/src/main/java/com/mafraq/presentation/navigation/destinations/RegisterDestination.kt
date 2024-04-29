package com.mafraq.presentation.navigation.destinations

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mafraq.presentation.features.authentication.ui.RegisterScreen
import com.mafraq.presentation.features.authentication.viewmodel.AuthViewModel
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.navigate
import com.mafraq.presentation.utils.extensions.sharedViewModel


fun NavController.navigateToRegister() {
    navigate(screen = Screen.Register, popBackstack = true)
}

fun NavGraphBuilder.registerDestination(navController: NavController, navigateToHome: () -> Unit) {
    composable(Screen.Register.route) {
        val viewModel = it.sharedViewModel<AuthViewModel>(navController = navController)
        RegisterScreen(
            viewModel = viewModel,
            navController = navController,
            navigateToHome = navigateToHome
        )
    }
}
