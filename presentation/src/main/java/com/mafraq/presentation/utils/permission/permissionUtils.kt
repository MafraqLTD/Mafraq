package com.mafraq.presentation.utils.permission

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.altaie.prettycode.core.utils.extenstions.isTrue
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.snackbar.LocalSnackState
import com.mafraq.presentation.design.components.snackbar.isInvisible
import com.mafraq.presentation.design.components.snackbar.showSnackbar
import com.mafraq.presentation.utils.extensions.openAppSettings
import dev.shreyaspatil.permissionFlow.PermissionState
import dev.shreyaspatil.permissionFlow.utils.launch
import dev.shreyaspatil.permissionflow.compose.rememberPermissionState
import dev.shreyaspatil.permissionflow.compose.rememberPermissionFlowRequestLauncher


@Composable
fun rememberPermissionState(
    permission: String,
    onGranted: () -> Unit = {},
    onDenied: () -> Unit = {},
    autoRequest: Boolean = false
): MafraqPermissionState {
    val snackState = LocalSnackState.current
    val permissionState by rememberPermissionState(permission = permission)
    val permissionLauncher = rememberPermissionFlowRequestLauncher {
        if (it[permission].isTrue)
            onGranted()
    }

    if (autoRequest && snackState.isInvisible && permissionState.isGranted.not())
        SideEffect {
            if (permissionState.isRationaleRequired.isTrue)
                onDenied()
            else
                permissionLauncher.launch(permission)
        }

    if (snackState.isInvisible
        && permissionState.isGranted.not()
        && permissionState.isRationaleRequired.isTrue) SideEffect{
        onDenied()
    }

    return MafraqPermissionState(
        permission = permission,
        isGranted = permissionState.isGranted,
        isRationaleRequired = permissionState.isRationaleRequired,
        request = { permissionLauncher.launch(permission) }
    )
}

data class MafraqPermissionState(
    val permission: String,
    val isGranted: Boolean,
    val isRationaleRequired: Boolean?,
    val request: () -> Unit
)
