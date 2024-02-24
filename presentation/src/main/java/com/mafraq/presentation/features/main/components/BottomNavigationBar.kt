package com.mafraq.presentation.features.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.navigation.NavigationBarItem
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.navigation.destinations.navigateToHome
import com.mafraq.presentation.navigation.destinations.navigateToNotifications
import com.mafraq.presentation.navigation.destinations.navigateToProfile
import com.mafraq.presentation.utils.extensions.currentRoute
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string


@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.currentRoute

    Box(modifier = Modifier.height(MafraqTheme.sizes.extraLarge)) {
        NavigationBar {
            val isHomeSelected = remember(currentRoute) { Screen.Home.route == currentRoute }
            NavigationBarItem(
                label = R.string.home.string,
                icon = R.drawable.home_outlined.painter,
                iconSelected = R.drawable.home_filled.painter,
                selected = isHomeSelected,
                onClick = navController::navigateToHome
            )

            val isProfileSelected = remember(currentRoute) { Screen.Profile.route == currentRoute }
            NavigationBarItem(
                label = R.string.profile.string,
                icon = R.drawable.profile_outlined.painter,
                iconSelected = R.drawable.profile_filled.painter,
                selected = isProfileSelected,
                onClick = navController::navigateToProfile
            )

            val isNotificationsSelected = remember(currentRoute) { Screen.Notifications.route == currentRoute }
            NavigationBarItem(
                label = R.string.notifications.string,
                icon = R.drawable.notification_outlined.painter,
                iconSelected = R.drawable.notification_filled.painter,
                selected = isNotificationsSelected,
                onClick = navController::navigateToNotifications
            )
        }
    }
}
