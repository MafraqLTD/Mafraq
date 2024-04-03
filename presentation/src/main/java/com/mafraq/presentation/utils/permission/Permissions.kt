package com.mafraq.presentation.utils.permission

import android.Manifest
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES


object Permissions {
    const val location = Manifest.permission.ACCESS_FINE_LOCATION
    val notification = if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU)
        Manifest.permission.POST_NOTIFICATIONS
    else null

    val all get() = listOfNotNull(location, notification)
}
