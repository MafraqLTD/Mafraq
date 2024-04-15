package com.mafraq.presentation.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.AppCard
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.SearchField
import com.mafraq.presentation.design.components.TextIcon
import com.mafraq.presentation.design.components.buttons.AppButtonIcon
import com.mafraq.presentation.design.theme.MafraqTheme.colors
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.design.theme.MafraqTheme.typography
import com.mafraq.presentation.features.home.components.AdsCarouselCard
import com.mafraq.presentation.features.home.components.SearchResultItem
import com.mafraq.presentation.features.home.components.VerificationStatus
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
        onLocationSatisfied = navController::navigateToMap,
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
            is HomeEvent.NavigateToMap -> locationRequester.request()
            is HomeEvent.NavigateToSupportChat -> navController.navigateToChatSupport()
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
            query = state.searchQuery,
            placeholder = R.string.searchـforـdestination.string,
            onQueryChange = listener::onSearchQueryChange,
            onClear = listener::onClearSearch,
        ) {
            state.placesSuggestions.forEach { place ->
                SearchResultItem(
                    title = place.name,
                    body = place.formattedAddress,
                    onClick = { listener.onSelectPlace(place) }
                )
            }
        }

        AdsCarouselCard(ads = state.ads, onClick = {})

        FindDriverCard(onClick = listener::navigateToMap)

        SupportCard(onClick = listener::navigateToSupportChat)
    }
}

@Composable
private fun FindDriverCard(onClick: () -> Unit) {
    val configurations = LocalConfiguration.current
    val cardHeight = with(LocalDensity.current) {
        (configurations.screenHeightDp / 1.75f).toDp()
    }

    AppCard(
        modifier = Modifier.height(cardHeight),
        verticalAlignment = Alignment.Top,
        rowContent = {
            Image(
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
                .padding(bottom = sizes.small, end = sizes.small),
            contentAlignment = Alignment.BottomEnd
        ) {
            AppButtonIcon(
                text = R.string.find_driver.string,
                onClick = onClick
            )
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
