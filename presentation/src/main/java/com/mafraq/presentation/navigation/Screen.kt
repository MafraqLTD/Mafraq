package com.mafraq.presentation.navigation

import com.mafraq.presentation.navigation.Screen.Map.ADDRESS_ID_ARG
import com.mafraq.presentation.navigation.Screen.Map.LATITUDE_ARG
import com.mafraq.presentation.navigation.Screen.Map.LONGITUDE_ARG


open class Screen(val route: String) {
    data object Home : Screen("home_screen")
    data object Profile : Screen("profile_screen") {
        val destination = withArgs(LATITUDE_ARG, LONGITUDE_ARG, ADDRESS_ID_ARG, isKey = true)
    }

    data object Notifications : Screen("notifications_screen")
    data object ChatSupport : Screen("chat_support_screen")
    data object ChatGroup : Screen("chat_group_screen")
    data object Map : Screen("map_screen") {
        const val IS_FROM_PROFILE_ARG = "is_from_profile"
        const val LATITUDE_ARG = "latitude"
        const val LONGITUDE_ARG = "longitude"
        const val ADDRESS_ID_ARG = "address_id"
        val destination = withArgs(LATITUDE_ARG, LONGITUDE_ARG, ADDRESS_ID_ARG, IS_FROM_PROFILE_ARG, isKey = true)
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
