package com.mafraq.presentation.features.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mafraq.presentation.design.components.navigation.LocalNavigationProvider
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.features.main.components.BottomNavigationBar
import com.mafraq.presentation.navigation.NavigationHostGraph
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.currentRoute


@Composable
fun App(isAuthorized: Boolean) {

    MafraqTheme {
        val navController = LocalNavigationProvider.current
        val backStackEntry by navController.currentBackStackEntryAsState()
        val showNavigationBar = with(backStackEntry) {
            currentRoute in listOf(
                Screen.Home.route,
                Screen.Profile.route,
                Screen.Notifications.route,
            )
        }

        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing,
            bottomBar = {
                AnimatedVisibility(
                    visible = showNavigationBar,
                    enter = EnterTransition.None,
                    exit = ExitTransition.None
                ) { BottomNavigationBar(navController = navController) }
            }
        ) { paddingValues ->
            NavigationHostGraph(
                navController = navController,
                startDestination = if (isAuthorized) Screen.Home else Screen.Login,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}
