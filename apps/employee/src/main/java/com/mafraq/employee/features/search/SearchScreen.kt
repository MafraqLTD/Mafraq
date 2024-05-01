package com.mafraq.employee.features.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.SearchField
import com.mafraq.presentation.design.components.container.Loading
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.employee.features.search.components.SearchResultItem
import com.mafraq.employee.navigation.destinations.navigateToMap
import com.mafraq.presentation.utils.extensions.Listen
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string
import com.mafraq.presentation.utils.rememberLocationRequester


@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navController: NavController
) {
    val state: SearchUiState by viewModel.state.collectAsStateWithLifecycle()
    val event: SearchEvent? by viewModel.event.collectAsState(null)
    val listener: SearchInteractionListener = viewModel

    val locationRequester = rememberLocationRequester(
        onLocationSatisfied = {
            val destination = viewModel.mapDestination
            navController.navigateToMap(
                latitude = destination.latitude.toFloat(),
                longitude = destination.longitude.toFloat(),
                fromProfile = viewModel.isFromProfile
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
            SearchEvent.NavigateBack -> navController.navigateUp()
            SearchEvent.NavigateToMap -> locationRequester.request()
        }
    }
}

@Composable
fun Content(
    state: SearchUiState = SearchUiState(),
    listener: SearchInteractionListener = SearchInteractionListener.Preview
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }

    SearchField(
        query = state.searchQuery,
        active = true,
        placeholder = R.string.searchـforـdestination.string,
        onQueryChange = listener::onSearchQueryChange,
        onActiveChange = {},
        onClear = listener::onClearSearch,
        leadingIcon = R.drawable.ic_back_arrow.painter,
        onLeadingClick = {
            focusManager.clearFocus()
            listener.navigateBack()
        },
        modifier = Modifier.focusRequester(focusRequester)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(sizes.small),
            verticalArrangement = Arrangement.spacedBy(sizes.small),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = state.placesSuggestions) { place ->
                SearchResultItem(
                    title = place.name,
                    body = place.formattedAddress,
                    onClick = { listener.onSelectPlace(place) }
                )
            }
        }
    }

    Loading(showDialog = state.isLoading, hintText = R.string.please_wait.string)
}


@Preview(showBackground = true)
@Composable
private fun Preview() = ColumnPreview {
    Content()
}
