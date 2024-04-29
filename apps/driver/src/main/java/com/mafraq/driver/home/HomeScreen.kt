package com.mafraq.driver.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mafraq.driver.home.components.SubscriberCard
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.AppCard
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.components.TextIcon
import com.mafraq.presentation.design.components.home.AdsCarouselCard
import com.mafraq.presentation.design.components.home.VerificationStatus
import com.mafraq.presentation.design.theme.MafraqTheme.colors
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.design.theme.MafraqTheme.typography
import com.mafraq.presentation.navigation.destinations.navigateToChatGroup
import com.mafraq.presentation.navigation.destinations.navigateToChatSupport
import com.mafraq.presentation.navigation.destinations.navigateToMap
import com.mafraq.presentation.utils.extensions.Listen
import com.mafraq.presentation.utils.extensions.detectTapGestures
import com.mafraq.presentation.utils.extensions.painter
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

    SideEffect(listener::reload)

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .detectTapGestures(onTap = { focusManager.clearFocus() })
            .padding(horizontal = sizes.medium)
            .padding(top = sizes.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(sizes.medium)
    ) {

        AdsCarouselCard(ads = state.ads, onClick = {})

        SupportCard(onClick = listener::navigateToSupportChat)


        Column {
            Text(
                text = R.string.subscribe_requests.string,
                style = typography.titleMedium
            )
            Spacer.Small()
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(space = sizes.medium)
            ) {
                items(items = state.ads) {
                    SubscriberCard(
                        profilePic = it.imageUrl,
                        name = "Mostafa mohamed",
                        collageName = "collageName",
                        phone = "05532415",
                        title = "Subscribed",
                        area = "Zhoor",
                        distance = "1.4 km",
                        workDays = "Mon, Sun, Tus",
                        cancelButtonTitle = R.string.cancel.string
                    )
                }
            }
        }
    }
}

@Composable
private fun SupportCard(onClick: () -> Unit) {
    AppCard(
        modifier = Modifier.clickable(onClick = onClick),
        containerColor = colors.onPrimary,
        contentPadding = PaddingValues(sizes.medium),
        rowContent = {
            Column {
                Text(
                    text = R.string.have_questions.string,
                    color = colors.contentPrimary,
                    style = typography.titleMedium
                )
                TextIcon(
                    text = R.string.chat_with_support.string,
                    style = typography.titleSmall,
                    icon = R.drawable.ic_forward_arrow.painter,
                    onClick = onClick,
                )
            }

            Image(
                painter = R.drawable.chat.painter,
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() = ColumnPreview {
    Content()
}
