package com.mafraq.presentation.design.components.navigation

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import com.mafraq.presentation.utils.extensions.optionalComposable

@Composable
fun RowScope.NavigationBarItem(
    icon: Painter,
    modifier: Modifier = Modifier,
    iconSelected: Painter? = null,
    selected: Boolean = false,
    onClick: () -> Unit = {},
    label: String? = null,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    colors: NavigationBarItemColors = NavigationBarItemDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        label = label?.optionalComposable {
            Text(
                text = it,
                color = if (selected) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.W500
                )
            )
        },
        icon = {
                Icon(
                    painter = if (selected) iconSelected ?: icon else icon,
                    contentDescription = label,
                    tint = if (selected) MaterialTheme.colorScheme.primary else Color.Unspecified
                )
        },
        modifier = modifier,
        enabled = enabled,
        alwaysShowLabel = alwaysShowLabel,
        colors = colors,
        interactionSource = interactionSource
    )
}
