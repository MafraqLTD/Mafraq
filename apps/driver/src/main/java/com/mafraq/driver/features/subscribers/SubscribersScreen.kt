package com.mafraq.driver.features.subscribers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mafraq.data.utils.formatted
import com.mafraq.driver.features.home.components.SubscriberCard
import com.mafraq.driver.main.components.LocalAppStateProvider
import com.mafraq.driver.navigation.destinations.navigateToMap
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.design.theme.MafraqTheme.typography
import com.mafraq.presentation.navigation.destinations.navigateToChatGroup
import com.mafraq.presentation.utils.extensions.Listen
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string
import com.mafraq.presentation.utils.rememberLocationRequester


@Composable
fun SubscribersScreen(
    viewModel: SubscribersViewModel,
    navController: NavController
) {
    val state: SubscribersUiState by viewModel.state.collectAsStateWithLifecycle()
    val event: SubscribersEvent? by viewModel.event.collectAsState(null)
    val listener: SubscribersInteractionListener = viewModel

    if (LocalAppStateProvider.current.hasSubscribers.not()) SideEffect {
        navController.navigateUp()
    }

    val locationRequester = rememberLocationRequester(
        onLocationSatisfied = {
            val destination = viewModel.mapDestination ?: return@rememberLocationRequester
            navController.navigateToMap(
                latitude = destination.latitude.toFloat(),
                longitude = destination.longitude.toFloat()
            )
        },
        locationSettingsDelegate = viewModel
    )

    Content(
        state = state,
        listener = listener
    )

    event?.Listen { currentEvent ->
        when (currentEvent) {
            SubscribersEvent.NavigateToMap -> locationRequester.request()
            SubscribersEvent.NavigateToChatGroup -> navController.navigateToChatGroup(popBackstack = false)
        }
    }
}

@Composable
fun Content(
    state: SubscribersUiState = SubscribersUiState(),
    listener: SubscribersInteractionListener = SubscribersInteractionListener.Preview
) {
    val pendingSubscribers by state.pendingFlow.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(sizes.medium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = R.string.manage_your_subscribers.string,
                style = typography.titleLarge
            )

            IconButton(
                onClick = listener::navigateToGroupChat,
                modifier = Modifier.offset(x = sizes.medium)
            ) {
                Image(
                    painter = R.drawable.chat.painter,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer.Medium()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(space = sizes.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = pendingSubscribers) { item ->
                SubscriberCard(
                    profilePic = item.imageUrl,
                    name = item.name,
                    workPlace = item.workLocation.formattedAddress,
                    phone = item.phone,
                    status = R.string.subscribed.string,
                    homePlace = item.homeLocation.formattedAddress,
                    distance = "-",
                    offDays = item.offDays.formatted,
                    cancelButtonTitle = R.string.unsubscribe.string,
                    onLocationClick = { listener.navigateToMap(item) },
                    onCancelClick = { listener.unsubscribe(item) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() = ColumnPreview {
    Content()
}
