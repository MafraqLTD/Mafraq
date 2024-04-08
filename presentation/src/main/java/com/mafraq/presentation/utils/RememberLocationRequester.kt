package com.mafraq.presentation.utils

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.snackbar.LocalSnackState
import com.mafraq.presentation.design.components.snackbar.showSnackbar
import com.mafraq.presentation.utils.extensions.openAppSettings
import com.mafraq.presentation.utils.location.LocationSettingsDelegate
import com.mafraq.presentation.utils.permission.Permissions
import com.mafraq.presentation.utils.permission.rememberPermissionState


data class LocationRequesterState(
    val isSatisfied: Boolean,
    val isPermissionGranted: Boolean,
    val isLocationEnabled: Boolean,
    val request: () -> Unit
)


@Composable
fun rememberLocationRequester(
    onLocationSatisfied: () -> Unit = {},
    locationSettingsDelegate: LocationSettingsDelegate
): LocationRequesterState {
    val context = LocalContext.current
    val snackState = LocalSnackState.current
    val permissionState = rememberPermissionState(
        autoRequest = false,
        permission = Permissions.location,
        onGranted = onLocationSatisfied,
        onDenied = {
            snackState.showSnackbar(
                message = context.getString(R.string.app_permission_denied),
                actionLabel = context.getString(R.string.settings),
                duration = SnackbarDuration.Long,
                onAccept = context::openAppSettings,
            )
        }
    )

    var requestEnablingLocationCallback: () -> Unit by remember { mutableStateOf({}) }

    val resultLauncher = rememberActivityLauncher(
        onAccepted = {
            if (permissionState.isGranted)
                onLocationSatisfied()
            else
                permissionState.request()
        },
        onRejected = {
            snackState.showSnackbar(
                message = context.getString(R.string.location_service_disabled),
                actionLabel = context.getString(R.string.enable_location),
                duration = SnackbarDuration.Long,
                onAccept = requestEnablingLocationCallback,
            )
        },
    )

    requestEnablingLocationCallback = {
        if (locationSettingsDelegate.isLocationEnabled)
            permissionState.request()
        else
            locationSettingsDelegate.requestEnableLocation(resultLauncher)
    }

    return remember{
        LocationRequesterState(
            isSatisfied = locationSettingsDelegate.isLocationSettingSatisfied,
            isLocationEnabled = locationSettingsDelegate.isLocationSettingSatisfied,
            isPermissionGranted = permissionState.isGranted,
            request = requestEnablingLocationCallback
        )
    }
}
