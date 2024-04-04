package com.mafraq.presentation.utils.location

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.core.content.ContextCompat
import com.altaie.gls.GLSManager
import com.mafraq.presentation.utils.extensions.locationManager
import com.mafraq.presentation.utils.permission.Permissions
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocationSettingsDelegateImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val glsManager: GLSManager,
) : LocationSettingsDelegate {
    override val isLocationEnabled: Boolean
        get() = context.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    override val isLocationSettingSatisfied: Boolean
        get() = isLocationPermissionGranted && isLocationEnabled

    override val isLocationPermissionGranted: Boolean
        get() = ContextCompat.checkSelfPermission(
            context,
            Permissions.location
        ) == PackageManager.PERMISSION_GRANTED

    override fun requestEnableLocation(
        resultLauncher: ActivityResultLauncher<IntentSenderRequest>
    ): Boolean {
        if (isLocationEnabled) return true
        glsManager.requestLocationSettings(resultLauncher)
        return false
    }
}
