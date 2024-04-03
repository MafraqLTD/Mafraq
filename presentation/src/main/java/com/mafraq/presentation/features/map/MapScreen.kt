package com.mafraq.presentation.features.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mafraq.presentation.R
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.features.map.components.MapScreenWithMarkers
import com.mafraq.presentation.utils.extensions.Listen
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.toLatLng
import com.mafraq.presentation.utils.extensions.toPoint
import com.mafraq.presentation.utils.permission.Permissions
import com.mafraq.presentation.utils.permission.rememberPermissionState
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.plugin.animation.MapAnimationOptions


@Composable
fun MapScreen(viewModel: MapViewModel, navController: NavController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val event by viewModel.event.collectAsState(initial = null)
    val listener: MapInteractionListener = viewModel
    rememberPermissionState(
        autoRequest = true,
        permission = Permissions.location,
        onGranted = listener::onPermissionGranted
    )

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
    val zoomLevel = 15.0
    val mapAnimationOptions = remember { MapAnimationOptions.Builder().duration(1500L).build() }

    Box(modifier = Modifier.fillMaxSize()) {
        MapScreenWithMarkers(
            drivers = state.drivers,
            zoomLevel = zoomLevel,
            currentLocation = state.currentLocation.toLatLng(),
            onClick = listener::onDriverMarkClick,
            modifier = Modifier.fillMaxSize()
        ) {
            flyTo(
                cameraOptions = CameraOptions.Builder()
                    .center(state.currentLocation.toPoint())
                    .zoom(zoomLevel)
                    .build(),
                animationOptions = mapAnimationOptions
            )
        }

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
