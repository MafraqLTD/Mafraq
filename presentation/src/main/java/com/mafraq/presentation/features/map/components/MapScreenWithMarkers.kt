package com.mafraq.presentation.features.map.components

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.mafraq.data.entities.map.Driver
import com.mafraq.presentation.R
import com.mafraq.presentation.utils.extensions.drawableToBitmap
import com.mafraq.presentation.utils.extensions.toLatLng


@Composable
fun MapScreenWithMarkers(
    currentLocation: LatLng,
    drivers: List<Driver>,
    onClick: (driver: Driver) -> Unit,
    modifier: Modifier = Modifier,
    zoomLevel: Float = 10f,
    @DrawableRes
    markerIconResId: Int = R.drawable.map_mark
) {
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation, zoomLevel)
    }

    val markerIcon = remember {
        val bitmap = context.drawableToBitmap(markerIconResId)
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState
    ) {
        drivers.forEach { driver ->
            Marker(
                state = MarkerState(driver.location.toLatLng()),
                icon = markerIcon,
                title = driver.name,
                snippet = driver.snippet,
                onClick = {
                    onClick(driver)
                    true
                }
            )
        }
    }
}
