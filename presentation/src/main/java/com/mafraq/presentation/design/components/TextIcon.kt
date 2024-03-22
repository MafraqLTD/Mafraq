package com.mafraq.presentation.design.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.clickableNoRipple


@Composable
fun TextIcon(
    text: String,
    icon: @Composable () -> Unit,
    style: TextStyle = MaterialTheme.typography.labelLarge,
    contentColor: Color = MafraqTheme.colors.primary,
    contentPadding: PaddingValues = PaddingValues(),
    onClick: (() -> Unit)? = null
) {
    ProvideContentColorTextStyle(
        contentColor = contentColor,
        textStyle = style
    ) {
        Row(
            Modifier
                .then(
                    if (onClick != null)
                        Modifier.clickableNoRipple(onClick = onClick)
                    else
                        Modifier
                )
                .defaultMinSize(
                    minWidth = ButtonDefaults.MinWidth,
                    minHeight = ButtonDefaults.MinHeight
                )
                .padding(contentPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center
            )

            icon()
        }
    }
}


@Composable
fun TextIcon(
    text: String,
    icon: ImageVector,
    style: TextStyle = MaterialTheme.typography.labelLarge,
    contentColor: Color = MafraqTheme.colors.primary,
    contentPadding: PaddingValues = PaddingValues(),
    onClick: (() -> Unit)? = null
) = TextIcon(
    text = text,
    style = style,
    onClick = onClick,
    contentColor = contentColor,
    contentPadding = contentPadding,
    icon = {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(MafraqTheme.sizes.medium)
        )
    }
)

@Composable
fun TextIcon(
    text: String,
    icon: Painter,
    style: TextStyle = MaterialTheme.typography.labelLarge,
    contentColor: Color = MafraqTheme.colors.primary,
    contentPadding: PaddingValues = PaddingValues(),
    onClick: (() -> Unit)? = null
) = TextIcon(
    text = text,
    style = style,
    onClick = onClick,
    contentColor = contentColor,
    contentPadding = contentPadding,
    icon = {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(MafraqTheme.sizes.medium)
        )
    }
)

@Composable
internal fun ProvideContentColorTextStyle(
    contentColor: Color,
    textStyle: TextStyle,
    content: @Composable () -> Unit
) {
    val mergedStyle = LocalTextStyle.current.merge(textStyle)
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalTextStyle provides mergedStyle,
        content = content
    )
}
