package com.mafraq.employee.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mafraq.presentation.design.components.navigation.LocalNavigationProvider
import com.mafraq.presentation.design.components.snackbar.LocalSnackState
import com.mafraq.presentation.design.components.snackbar.Snackbar
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.employee.main.components.BottomNavigationBar
import com.mafraq.employee.main.components.LocalAppStateProvider
import com.mafraq.employee.navigation.NavigationHostGraph
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.currentRoute


@Composable
fun App(
    isAuthorized: Boolean,
    isProfileFilled: Boolean,
) {

    MafraqTheme {
        val navController = LocalNavigationProvider.current
        val backStackEntry by navController.currentBackStackEntryAsState()
        val showNavigationBar = with(backStackEntry) {
            currentRoute in listOf(
                Screen.Home.route,
                Screen.ChatGroup.route,
                Screen.Profile.route,
                Screen.Profile.destination.route,
                Screen.Notifications.route,
            )
        }

        fun getStartDestination() = when {
            !isAuthorized -> Screen.Login
            isProfileFilled -> Screen.Home
            else -> Screen.LoginProfile
        }

        Scaffold(
            contentWindowInsets = WindowInsets.safeDrawing,
            snackbarHost = {
                SnackbarHost(hostState = LocalSnackState.current) {
                    Snackbar(
                        snackbarData = it,
                        actionContentColor = MaterialTheme.colorScheme.inverseSurface,
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier.padding(horizontal = sizes.large),
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        actionColor = colors.primary,
                    )
                }
            },
            bottomBar = {
                AnimatedVisibility(
                    visible = showNavigationBar,
                    enter = EnterTransition.None,
                    exit = ExitTransition.None
                ) {
                    BottomNavigationBar(navController = navController)
                }
            }
        ) { paddingValues ->
            NavigationHostGraph(
                navController = navController,
                startDestination = getStartDestination(),
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}
