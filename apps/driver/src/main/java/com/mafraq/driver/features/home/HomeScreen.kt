package com.mafraq.driver.features.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mafraq.data.utils.formatted
import com.mafraq.driver.features.home.components.SubscriberCard
import com.mafraq.driver.main.components.LocalAppStateProvider
import com.mafraq.driver.navigation.destinations.navigateToMap
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.components.home.AdsCarouselCard
import com.mafraq.presentation.design.components.home.CarCard
import com.mafraq.presentation.design.components.home.SupportCard
import com.mafraq.presentation.design.components.home.VerificationStatus
import com.mafraq.presentation.design.theme.MafraqTheme.colors
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.design.theme.MafraqTheme.typography
import com.mafraq.presentation.navigation.destinations.navigateToChatGroup
import com.mafraq.presentation.navigation.destinations.navigateToChatSupport
import com.mafraq.presentation.utils.extensions.Listen
import com.mafraq.presentation.utils.extensions.detectTapGestures
import com.mafraq.presentation.utils.extensions.string
import com.mafraq.presentation.utils.rememberLocationRequester


@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavController
) {
    val state: HomeUiState by viewModel.state.collectAsStateWithLifecycle()
    val event: HomeEvent? by viewModel.event.collectAsState(null)
    val listener: HomeInteractionListener = viewModel

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

    VerificationStatus(
        verified = viewModel.isEmailVerified,
        onDoneClicked = viewModel::onVerificationDone
    ) {
        Content(
            state = state,
            listener = listener
        )
    }
    event?.Listen { currentEvent ->
        when (currentEvent) {
            HomeEvent.NavigateToMap -> locationRequester.request()
            HomeEvent.NavigateToChatGroup -> navController.navigateToChatGroup()
            HomeEvent.NavigateToSupportChat -> navController.navigateToChatSupport()
        }
    }
}

@Composable
fun Content(
    state: HomeUiState = HomeUiState(),
    listener: HomeInteractionListener = HomeInteractionListener.Preview
) {
    val focusManager = LocalFocusManager.current
    val scrollState: LazyListState = rememberLazyListState()
    val showTitleSpacer by remember { derivedStateOf { scrollState.firstVisibleItemIndex > 2 } }
    val pendingSubscribers by state.pendingFlow.collectAsState(initial = emptyList())
    val hasSubscribers = LocalAppStateProvider.current.hasSubscribers

    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .fillMaxSize()
            .detectTapGestures(onTap = { focusManager.clearFocus() })
            .padding(top = sizes.medium)
            .padding(horizontal = sizes.medium),
        verticalArrangement = Arrangement.spacedBy(space = sizes.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            AdsCarouselCard(ads = state.ads, onClick = {})
        }

        if (hasSubscribers)
            item {
                CarCard(
                    text = R.string.chat_with_your_subscribers.string,
                    onClick = listener::navigateToGroupChat
                )
            }

        item {
            SupportCard(onClick = listener::navigateToSupportChat)
        }

        if (pendingSubscribers.isNotEmpty())
            stickyHeader {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colors.background)
                ) {
                    Text(
                        text = R.string.subscribe_requests.string,
                        style = typography.titleMedium
                    )

                    AnimatedVisibility(showTitleSpacer) {
                        Spacer.Small()
                    }
                }
            }

        items(items = pendingSubscribers) { item ->
            SubscriberCard(
                profilePic = item.imageUrl,
                name = item.name,
                workPlace = item.workLocation.formattedAddress,
                phone = item.phone,
                status = R.string.pending.string,
                statusColor = colors.warning,
                homePlace = item.homeLocation.formattedAddress,
                distance = "-",
                offDays = item.offDays.formatted,
                cancelButtonTitle = R.string.cancel.string,
                onLocationClick = { listener.navigateToMap(item) },
                onCancelClick = { listener.cancelSubscribeRequest(item) }
            )
        }

        item {
            Spacer.Small()
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() = ColumnPreview {
    Content()
}
