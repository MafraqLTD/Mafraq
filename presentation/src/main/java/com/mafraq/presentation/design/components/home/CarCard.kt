package com.mafraq.presentation.design.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.AppCard
import com.mafraq.presentation.design.components.buttons.AppButtonIcon
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.painter

@Composable
fun CarCard(
    text: String,
    onClick: () -> Unit,
    containerColor: Color = MafraqTheme.colors.primary,
    rowContent: @Composable (RowScope.() -> Unit)? = null,
    content: @Composable (ColumnScope.() -> Unit)? = null
) {
    val configurations = LocalConfiguration.current
    val cardHeight = with(LocalDensity.current) {
        (configurations.screenHeightDp / 1.75f).toDp()
    }

    AppCard(
        modifier = Modifier.height(cardHeight),
        rowModifier = { Modifier.weight(1f) },
        verticalAlignment = Alignment.Top,
        containerColor = containerColor,
        rowContent = {
            rowContent?.invoke(this) ?: Image(
                painter = R.drawable.car.painter,
                modifier = Modifier
                    .height(cardHeight / 1.35f)
                    .absoluteOffset(x = (-20).dp)
                    .aspectRatio(1f),
                contentDescription = null,
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MafraqTheme.sizes.small, end = MafraqTheme.sizes.small),
            contentAlignment = Alignment.BottomEnd
        ) {
            AppButtonIcon(
                text = text,
                onClick = onClick
            )
        }

        content?.invoke(this)
    }
}