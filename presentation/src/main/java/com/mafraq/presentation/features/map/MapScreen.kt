package com.mafraq.presentation.features.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.withResumed
import androidx.navigation.NavController
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.container.Loading
import com.mafraq.presentation.design.components.snackbar.LocalSnackState
import com.mafraq.presentation.design.components.snackbar.showSnackbar
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.features.map.components.DriverBottomSheet
import com.mafraq.presentation.features.map.components.MapScreenWithMarkers
import com.mafraq.presentation.utils.extensions.Listen
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string
import com.mafraq.presentation.utils.rememberLocationRequester


@Composable
fun MapScreen(viewModel: MapViewModel, navController: NavController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val event by viewModel.event.collectAsState(initial = null)
    val listener: MapInteractionListener = viewModel

    val locationRequester = rememberLocationRequester(
        onLocationSatisfied = listener::updateLocation,
        locationSettingsDelegate = viewModel
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(key1 = Unit) {
        lifecycleOwner.withResumed {
            locationRequester.request()
        }
    }

    Loading(showDialog = state.isLoading, hintText = R.string.finding_best_route.string)

    Content(state, listener)

    event?.Listen {
        when (it) {
            MapEvent.OnNavigateBack -> navController.navigateUp()
        }
    }
}


@Composable
private fun Content(
    state: MapUiState = MapUiState(),
    listener: MapInteractionListener = MapInteractionListener.Preview
) {
    val zoomLevel = state.zoomLevel
    val snackbarHostState = LocalSnackState.current
    val context = LocalContext.current

    if (state.isDestination)
        LaunchedEffect(key1 = Unit) {
            snackbarHostState.showSnackbar(
                message = context.getString(R.string.set_destination),
                actionLabel = context.getString(R.string.confirm),
                onAccept = listener::onConfirmDestination
            )
        }

    if (state.showDriverDetails)
        with(state.selectedDriver) {
            DriverBottomSheet(
                car = car,
                name = fullName,
                rating = rating,
                carNumber = carNumber,
                profilePic = profilePicture,
                snippet = snippet,
                onDismissRequest = listener::onDismissDriverDetails,
            )
        }

    Box(modifier = Modifier.fillMaxSize()) {
        MapScreenWithMarkers(
            drivers = state.availableDrivers,
            zoomLevel = zoomLevel,
            onClick = listener::onDriverMarkClick,
            modifier = Modifier.fillMaxSize(),
            isDestination = state.isDestination,
            directions = state.directions,
            currentLocation = state.currentLocation,
            onMapClicked = listener::onMapClicked
        )

        IconButton(
            onClick = listener::onNavigateBack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(sizes.extraSmall)
        ) {
            Icon(
                painter = R.drawable.ic_back_arrow.painter,
                contentDescription = null
            )
        }
    }
}
