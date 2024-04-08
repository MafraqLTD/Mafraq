package com.mafraq.presentation.features.map.components

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.LatLng
import com.mafraq.data.entities.map.Driver
import com.mafraq.presentation.R
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.drawableToBitmap
import com.mafraq.presentation.utils.extensions.toPoint
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.style.layers.properties.generated.TextAnchor
import com.mapbox.maps.extension.style.layers.properties.generated.TextJustify
import com.mapbox.maps.plugin.animation.MapAnimationOptions


@OptIn(MapboxExperimental::class)
@Composable
fun MapScreenWithMarkers(
    currentLocation: LatLng,
    drivers: List<Driver>,
    onClick: (driver: Driver) -> Unit,
    modifier: Modifier = Modifier,
    zoomLevel: Double = 15.0,
    @DrawableRes
    markerIconResId: Int = R.drawable.map_mark,
) {
    val context = LocalContext.current
    val cameraPositionState = rememberMapViewportState {
        setCameraOptions {
            zoom(zoomLevel)
            center(currentLocation.toPoint())
            bearing(0.0)
            pitch(0.0)
        }
    }

    val mapAnimationOptions = remember { MapAnimationOptions.Builder().duration(1500L).build() }

    LaunchedEffect(key1 = currentLocation) {
        cameraPositionState.flyTo(
            cameraOptions = CameraOptions.Builder()
                .center(currentLocation.toPoint())
                .zoom(zoomLevel)
                .build(),
            animationOptions = mapAnimationOptions
        )
    }

    val markerIcon = remember { context.drawableToBitmap(markerIconResId) }

    MapboxMap(
        modifier = modifier,
        mapViewportState = cameraPositionState,
        logo = {},
        compass = {},
        scaleBar = {},
        attribution = {},
    ) {
        drivers.forEach { driver ->
            val point = driver.location.toPoint()
            PointAnnotation(
                iconImageBitmap = markerIcon,
                iconSize = 1.3,
                textSize = 11.5,
                point = point,
                textField = driver.car,
                textOffset = listOf(0.0, -3.5),
                textAnchor = TextAnchor.TOP,
                textJustify = TextJustify.CENTER,
                textColorInt = MafraqTheme.colors.primary.toArgb(),
                onClick = {
                    onClick(driver)
                    true
                }
            )
        }
    }
}
