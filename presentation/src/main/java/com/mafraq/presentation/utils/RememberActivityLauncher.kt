package com.mafraq.presentation.utils

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable


@Composable
fun rememberActivityLauncher(
    onAccepted: () -> Unit = {},
    onRejected: () -> Unit = {}
): ActivityResultLauncher<IntentSenderRequest> = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.StartIntentSenderForResult(),
    onResult = {
        if (it.resultCode == Activity.RESULT_OK)
            onAccepted()
        else
            onRejected()
    }
)
