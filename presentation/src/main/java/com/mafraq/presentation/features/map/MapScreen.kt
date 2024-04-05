package com.mafraq.presentation.features.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mafraq.presentation.R
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.features.map.components.DriverBottomSheet
import com.mafraq.presentation.features.map.components.MapScreenWithMarkers
import com.mafraq.presentation.utils.extensions.Listen
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.toLatLng
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

    LifecycleResumeEffect {
        locationRequester.request()
        onPauseOrDispose { listener.cancelLocationUpdates() }
    }

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
    val zoomLevel = 13.0

    with(state.selectedDriver) {
        DriverBottomSheet(
            car = car,
            name = name,
            rating = rating,
            carNumber = carNumber,
            profilePic = profilePic,
            isVisible = state.showDriverDetails,
            onDismissRequest = listener::onDismissDriverDetails,
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        MapScreenWithMarkers(
            drivers = state.availableDrivers,
            zoomLevel = zoomLevel,
            currentLocation = state.currentLocation.toLatLng(),
            onClick = listener::onDriverMarkClick,
            modifier = Modifier.fillMaxSize()
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
