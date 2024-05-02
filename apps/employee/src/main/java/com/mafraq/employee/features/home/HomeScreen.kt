package com.mafraq.employee.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mafraq.presentation.design.components.home.CarCard
import com.mafraq.employee.features.home.components.PendingSubscribeRequest
import com.mafraq.presentation.design.components.home.SupportCard
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.SearchField
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.design.components.home.AdsCarouselCard
import com.mafraq.presentation.design.components.home.VerificationStatus
import com.mafraq.presentation.navigation.destinations.navigateToChatSupport
import com.mafraq.employee.navigation.destinations.navigateToMap
import com.mafraq.employee.navigation.destinations.navigateToSearch
import com.mafraq.presentation.navigation.destinations.navigateToChatGroup
import com.mafraq.presentation.utils.extensions.Listen
import com.mafraq.presentation.utils.extensions.clickableNoRipple
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
            navController.navigateToMap()
        },
        locationSettingsDelegate = viewModel
    )

    SideEffect (listener::reload)

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
            HomeEvent.NavigateToSearch -> navController.navigateToSearch()
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .detectTapGestures(onTap = { focusManager.clearFocus() })
            .padding(sizes.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(sizes.medium)
    ) {
        SearchField(
            query = "",
            active = false,
            enabled = false,
            placeholder = R.string.searchـforـdestination.string,
            onQueryChange = {},
            onClear = {},
            onActiveChange = {},
            modifier = Modifier.clickableNoRipple(onClick = listener::navigateToSearch)
        ) {}

        AdsCarouselCard(ads = state.ads, onClick = {})

        if (state.pendingDriver == null)
            CarCard(
                isSubscribed = state.isSubscribed,
                onFindDriverClick = listener::onFindDriver,
                onGroupChatClick = listener::navigateToGroupChat,
            )
        else
            PendingSubscribeRequest(state = state, onCancelClick=listener::cancelSubscribeRequest)

        SupportCard(onClick = listener::navigateToSupportChat)
    }
}


@Composable
private fun CarCard(
    isSubscribed: Boolean,
    onGroupChatClick: () -> Unit,
    onFindDriverClick: () -> Unit,
) {
    if (isSubscribed)
        CarCard(
            text = R.string.group_chat.string,
            onClick = onGroupChatClick
        )
    else
        CarCard(
            text = R.string.find_driver.string,
            onClick = onFindDriverClick
        )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() = ColumnPreview {
    Content()
}
