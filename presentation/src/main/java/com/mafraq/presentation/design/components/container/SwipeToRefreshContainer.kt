package com.mafraq.presentation.design.components.container

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.altaie.prettycode.core.utils.VoidCallBack
import com.mafraq.presentation.design.theme.MafraqTheme.colors


@Composable
fun SwipeToRefreshContainer(
    loadingState: Boolean = false,
    indicatorPadding: PaddingValues = PaddingValues(),
    onRefresh: VoidCallBack = {},
    content: @Composable BoxScope.() -> Unit
) {

    val pullRefreshState = rememberPullRefreshState(loadingState, { onRefresh() })

    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
        content()

        PullRefreshIndicator(
            refreshing = loadingState,
            state = pullRefreshState,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(indicatorPadding),
            contentColor = colors.primary,
            backgroundColor = colors.background
        )
    }
}
