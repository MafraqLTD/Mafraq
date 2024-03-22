package com.mafraq.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mafraq.presentation.navigation.destinations.chatDestination
import com.mafraq.presentation.navigation.destinations.homeDestination
import com.mafraq.presentation.navigation.destinations.loginDestination
import com.mafraq.presentation.navigation.destinations.mapDestination
import com.mafraq.presentation.navigation.destinations.notificationsDestination
import com.mafraq.presentation.navigation.destinations.profileDestination
import com.mafraq.presentation.navigation.destinations.registerDestination


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
    profileDestination(navController)
    loginDestination(navController)
    registerDestination(navController)
    notificationsDestination(navController)
    chatDestination(navController)
    mapDestination(navController)
}
