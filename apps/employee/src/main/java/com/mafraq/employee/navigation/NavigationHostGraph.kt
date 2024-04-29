package com.mafraq.employee.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.navigation.destinations.chatSupportDestination
import com.mafraq.presentation.navigation.destinations.chatGroupDestination
import com.mafraq.presentation.navigation.destinations.homeDestination
import com.mafraq.presentation.navigation.destinations.loginDestination
import com.mafraq.presentation.navigation.destinations.mapDestination
import com.mafraq.presentation.navigation.destinations.notificationsDestination
import com.mafraq.presentation.navigation.destinations.profileDestination
import com.mafraq.presentation.navigation.destinations.registerDestination
import com.mafraq.presentation.navigation.destinations.searchDestination


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
    profileDestination(navController)
    loginDestination(navController)
    registerDestination(navController)
    notificationsDestination(navController)
    chatSupportDestination(navController)
    chatGroupDestination(navController)
    mapDestination(navController)
}
