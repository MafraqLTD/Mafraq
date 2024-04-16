package com.mafraq.presentation.navigation


open class Screen(val route: String) {
    data object Home : Screen("home_screen")
    data object Profile : Screen("profile_screen")
    data object Notifications : Screen("notifications_screen")
    data object ChatSupport : Screen("chat_support_screen")
    data object ChatGroup : Screen("chat_group_screen")
    data object Map : Screen("map_screen") {
        const val LATITUDE_ARG = "latitude"
        const val LONGITUDE_ARG = "longitude"
        val destination = withArgs(LATITUDE_ARG, LONGITUDE_ARG, isKey = true)
    }
    data object Login : Screen("login_screen")
    data object Register : Screen("register_screen")

    internal fun withArgs(vararg args: Any, isKey: Boolean = false): Screen = Screen(
        buildString {
            append(route)
            args.forEach {
                append(if (isKey) "/{$it}" else "/$it")
            }
        }
    )
}
