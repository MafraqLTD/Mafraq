package com.mafraq.employee.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.navigation.destinations.chatSupportDestination
import com.mafraq.presentation.navigation.destinations.chatGroupDestination
import com.mafraq.employee.navigation.destinations.homeDestination
import com.mafraq.employee.navigation.destinations.loginDestination
import com.mafraq.employee.navigation.destinations.mapDestination
import com.mafraq.employee.navigation.destinations.navigateToHome
import com.mafraq.employee.navigation.destinations.navigateToMap
import com.mafraq.employee.navigation.destinations.navigateToSearch
import com.mafraq.presentation.navigation.destinations.notificationsDestination
import com.mafraq.presentation.navigation.destinations.profileDestination
import com.mafraq.employee.navigation.destinations.registerDestination
import com.mafraq.employee.navigation.destinations.searchDestination


@Composable
fun NavigationHostGraph(
    navController: NavHostController,
    startDestination: Screen,
    modifier: Modifier
) = NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = startDestination.route
) {
    homeDestination(navController)
    searchDestination(navController)
    profileDestination(
        navigateToMap = { fromProfile, addressId ->
            navController.navigateToMap(
                fromProfile = fromProfile,
                addressId = addressId
            )
        },
        navigateToHome = navController::navigateToHome,
        navigateToSearch = navController::navigateToSearch
    )
    loginDestination(navController, navigateToHome = navController::navigateToHome)
    registerDestination(navController, navigateToHome = navController::navigateToHome)
    notificationsDestination(navController)
    chatSupportDestination(navController)
    chatGroupDestination()
    mapDestination(navController)
}
