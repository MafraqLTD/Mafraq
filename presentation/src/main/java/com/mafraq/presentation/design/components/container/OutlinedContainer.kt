package com.mafraq.presentation.design.components.container

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.clickableNoRipple


@Composable
fun OutlinedContainer(
    modifier: Modifier = Modifier,
    label: String? = null,
    fieldHeight: Boolean = true,
    border: Boolean = true,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Column {
        label?.let {
            Text(
                text = label,
                modifier = Modifier.padding(bottom = MafraqTheme.sizes.small),
                style = MafraqTheme.typography.titleSmall,
            )
        }
        Surface(
            border = BorderStroke(
                color = MafraqTheme.colors.contentBorder,
                width = OutlinedTextFieldDefaults.UnfocusedBorderThickness,
            ).takeIf { border },
            modifier = modifier
                .fillMaxWidth()
                .run {
                    if (fieldHeight) height(OutlinedTextFieldDefaults.MinHeight) else this
                }
                .run {
                    if (onClick != null) clickableNoRipple(onClick = onClick) else this
                },
            shape = MafraqTheme.shapes.medium,
            content = content
        )
    }
}
