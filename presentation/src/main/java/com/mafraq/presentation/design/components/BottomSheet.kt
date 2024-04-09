package com.mafraq.presentation.design.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.utils.extensions.optionalComposable
import kotlinx.coroutines.launch

@Composable
fun BottomSheet(
    onDismissRequest: () -> Unit,
    sheetSwipeEnabled: Boolean = false,
    content: @Composable (ColumnScope.(hideSheet: () -> Unit) -> Unit)
) {
    val sheetState = rememberModalBottomSheetState { sheetSwipeEnabled }
    val scope = rememberCoroutineScope()

    fun hideSheet() {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                onDismissRequest()
            }
        }
    }

    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        sheetState = sheetState,
        dragHandle = optionalComposable(sheetSwipeEnabled) {
            BottomSheetDefaults.DragHandle()
        },
        content = {
            if (sheetSwipeEnabled.not())
                Spacer.Large()
            content(::hideSheet)
        },
    )
}