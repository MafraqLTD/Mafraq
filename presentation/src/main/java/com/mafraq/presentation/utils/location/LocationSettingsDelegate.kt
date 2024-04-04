package com.mafraq.presentation.utils.location

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest

interface LocationSettingsDelegate {
    val isLocationEnabled: Boolean
    val isLocationSettingSatisfied: Boolean
    val isLocationPermissionGranted: Boolean
    fun requestEnableLocation(resultLauncher: ActivityResultLauncher<IntentSenderRequest>): Boolean

    object DummyImpl : LocationSettingsDelegate {
        override val isLocationEnabled: Boolean = false
        override val isLocationSettingSatisfied: Boolean = false
        override val isLocationPermissionGranted: Boolean = false
        override fun requestEnableLocation(resultLauncher: ActivityResultLauncher<IntentSenderRequest>): Boolean = false
    }
}
