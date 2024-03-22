package com.mafraq.presentation.features.notifications.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.altaie.prettycode.core.utils.VoidCallBack
import com.mafraq.presentation.design.components.container.SwipeToRefreshContainer
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.design.theme.MafraqTheme.typography


@Composable
fun NoContentView(
    text: String,
    modifier: Modifier = Modifier,
    visible: Boolean = false,
    loadingState: Boolean = false,
    indicatorPadding: PaddingValues = PaddingValues(),
    onRefresh: VoidCallBack = {},
    content: (@Composable () -> Unit)? = null,
) {

    AnimatedVisibility(
        visible = visible && !loadingState,
        enter = fadeIn(tween(delayMillis = 300)),
        exit = fadeOut(tween(delayMillis = 300))
    ) {
        SwipeToRefreshContainer(
            loadingState = loadingState,
            indicatorPadding = indicatorPadding,
            onRefresh = onRefresh
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    sizes.small,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                content?.invoke()

                Text(
                    text = text,
                    style = typography.body,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}