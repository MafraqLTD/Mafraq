package com.mafraq.presentation.design.components.container

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.altaie.prettycode.core.utils.VoidCallBack
import com.mafraq.presentation.utils.extensions.clickableNoRipple


@Composable
fun ClickableContainer(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: Dp = 48.dp,
    shape: Shape = CircleShape,
    rippleEffect: Boolean = false,
    alignment: Alignment = Alignment.Center,
    onClick: VoidCallBack = {},
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        contentAlignment = alignment,
        modifier = modifier
            .size(size)
            .clip(shape)
            .then(
                if (rippleEffect)
                    Modifier.clickable(onClick = onClick, enabled = enabled)
                else
                    Modifier.clickableNoRipple(onClick = onClick, enabled = enabled)
            ),
        content = content
    )
}
