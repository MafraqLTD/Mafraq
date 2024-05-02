package com.mafraq.driver.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mafraq.driver.navigation.destinations.homeDestination
import com.mafraq.driver.navigation.destinations.loginDestination
import com.mafraq.driver.navigation.destinations.mapDestination
import com.mafraq.driver.navigation.destinations.navigateToHome
import com.mafraq.driver.navigation.destinations.navigateToMap
import com.mafraq.driver.navigation.destinations.registerDestination
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.navigation.destinations.chatSupportDestination
import com.mafraq.driver.navigation.destinations.subscribersDestination
import com.mafraq.presentation.navigation.destinations.chatGroupDestination
import com.mafraq.presentation.navigation.destinations.notificationsDestination
import com.mafraq.presentation.navigation.destinations.profileDestination


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
    mapDestination(navController)
    profileDestination(
        navigateToMap = { fromProfile, addressId ->
            navController.navigateToMap(
                fromProfile = fromProfile,
                addressId = addressId
            )
        },
        navigateToHome = navController::navigateToHome
    )
    loginDestination(navController, navigateToHome = navController::navigateToHome)
    registerDestination(navController, navigateToHome = navController::navigateToHome)
    notificationsDestination(navController)
    chatSupportDestination(navController)
    subscribersDestination(navController)
    chatGroupDestination(navController)
}
