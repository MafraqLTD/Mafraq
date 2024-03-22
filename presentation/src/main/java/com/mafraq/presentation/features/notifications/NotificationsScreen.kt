package com.mafraq.presentation.features.notifications

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.theme.MafraqTheme.colors
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.features.notifications.components.NoContentView
import com.mafraq.presentation.features.notifications.components.NotificationCard
import com.mafraq.presentation.utils.dummyData.Dummy.dummyNotifications
import com.mafraq.presentation.utils.extensions.Listen
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string


@Composable
fun NotificationsScreen(viewModel: NotificationsViewModel, navController: NavController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val event by viewModel.event.collectAsState(initial = null)
    val listener: NotificationsInteractionListener = viewModel

    Content(state, listener)

    event?.Listen {
        when(it){
            else -> {}
        }
    }
}


@Composable
private fun Content(
    state: NotificationsUiState = NotificationsUiState(),
    listener: NotificationsInteractionListener = NotificationsInteractionListener.Preview
) {
    NoContentView(
        text = R.string.no_notifications_found.string,
        visible = state.notifications.isEmpty(),
        onRefresh = listener::refreshNotifications,
        indicatorPadding = PaddingValues(vertical = sizes.extraSmall)
    ) {
        Image(
            painter = R.drawable.no_notifications.painter,
            contentDescription = null,
        )
    }

    LazyColumn {
        itemsIndexed(items = state.notifications) { index, item ->
            NotificationCard(
                title = item.title,
                body = item.body,
                date = item.createdAt,
                read = item.read,
                onClick = { listener.onNotificationClick(item.id) }
            )

            if (index < state.notifications.lastIndex)
                HorizontalDivider(color = colors.divider)
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun Preview() = ColumnPreview {
    Content(state = NotificationsUiState(dummyNotifications))
}
