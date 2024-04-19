package com.mafraq.presentation.utils.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptionsBuilder
import com.mafraq.presentation.navigation.Screen

fun NavController.backToHome() = runCatching {
    while (currentDestination?.route != Screen.Home.route)
        navigateUp()
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavController,
    screen: Screen? = null
): T {
    val navGraphRoute = screen?.route ?: return hiltViewModel()
    val parentEntry = remember(key1 = this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}

val NavBackStackEntry?.currentParentRoute
    get() = this?.destination?.parent?.route

val NavBackStackEntry?.currentRoute
    get() = this?.destination?.route

fun NavController.navigate(
    screen: Screen,
    keepBackStack: Boolean = true,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    if (currentBackStackEntry.currentRoute == screen.route) return

    navigate(screen.route) {
        if (!keepBackStack) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reelecting the same item
            launchSingleTop = true
            // Restore state when reelecting a previously selected item
            restoreState = true
        }
        apply(builder)
    }
}

fun NavController.navigate(
    screen: Screen,
    popBackstack: Boolean = false
) {
    navigate(
        route = screen.route,
        builder = {
            if (popBackstack)
                popUpTo(0) {
                    inclusive = true
                }
            launchSingleTop = true
        }
    )
}

fun SavedStateHandle.getArg(key: String) = get<String>(key).orEmpty()
fun<T> SavedStateHandle.getArgOrNull(key: String): T? = get(key)
