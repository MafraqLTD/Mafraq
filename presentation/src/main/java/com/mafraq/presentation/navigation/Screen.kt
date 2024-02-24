package com.mafraq.presentation.navigation


sealed class Screen(val route: String) {
    data object Home : Screen("home_screen")
    data object Profile : Screen("profile_screen")
    data object Notifications : Screen("notifications_screen")
    data object Chat : Screen("chat_screen")
    data object Map : Screen("map_screen")
    data object Login : Screen("login_screen")
    data object Register : Screen("register_screen")
}