package com.mafraq.presentation.design.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch

@Composable
fun BottomSheet(
    onDismissRequest: () -> Unit,
    isVisible: Boolean,
    content: @Composable (ColumnScope.(hideSheet: () -> Unit) -> Unit)
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(isVisible) }

    if (showBottomSheet.not()) return

    fun hideSheet() {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                showBottomSheet = false
                onDismissRequest()
            }
        }
    }

    ModalBottomSheet(
        onDismissRequest = {
            showBottomSheet = false
            onDismissRequest()
        },
        sheetState = sheetState,
        content = { content(::hideSheet) }
    )
}