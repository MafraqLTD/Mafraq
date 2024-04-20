package com.mafraq.presentation.design.components.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mafraq.presentation.design.theme.MafraqTheme.sizes


@Composable
fun Loading(
    showDialog: Boolean,
    modifier: Modifier = Modifier,
    size: Dp = 100.dp,
    hintText: String? = null,
    testTag: String = "dialog",
    shape: Shape = MaterialTheme.shapes.medium,
    contentAlignment: Alignment = Alignment.Center,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    foregroundColor: Color = MaterialTheme.colorScheme.primary,
    onCompleted: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (showDialog) {
            Dialog(
                onDismissRequest = { },
                DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
            ) {
                Box(
                    contentAlignment = contentAlignment,
                    modifier = Modifier
                        .testTag(testTag)
                        .sizeIn(minWidth = size, minHeight = size)
                        .background(
                            color = backgroundColor,
                            shape = shape
                        )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(sizes.medium)
                    ) {
                        CircularProgressIndicator(
                            color = foregroundColor
                        )

                        hintText?.let {
                            Spacer(modifier = Modifier.height(sizes.medium))
                            Text(
                                text = hintText,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        } else {
            SideEffect(onCompleted)
        }

        content()
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    Loading(showDialog = true, hintText = "Loading") {}
}
