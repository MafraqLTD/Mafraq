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
    onGranted: () -> Unit,
    autoRequest: Boolean = false
): PermissionState {
    val context = LocalContext.current
    val snackState = LocalSnackState.current
    val permissionState by rememberPermissionState(permission = permission)
    val permissionLauncher = rememberPermissionFlowRequestLauncher {
        if (it[permission].isTrue) onGranted()
    }

    if (autoRequest && snackState.isInvisible && permissionState.isGranted.not())
        SideEffect {
            if (permissionState.isRationaleRequired.isTrue)
                snackState.showSnackbar(
                    message = context.getString(R.string.app_permission_denied),
                    actionLabel = context.getString(R.string.settings),
                    duration = SnackbarDuration.Long,
                    onAccept = context::openAppSettings,
                )
            else
                permissionLauncher.launch(permission)
        }

    return permissionState
}
