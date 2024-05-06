package com.mafraq.employee.features.map.components

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.map.Location
import com.mafraq.data.remote.mappers.toLocation
import com.mafraq.data.remote.mappers.toPoint
import com.mafraq.presentation.R
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.drawableToBitmap
import com.mafraq.presentation.utils.extensions.middle
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.generated.PolylineAnnotation
import com.mapbox.maps.extension.style.layers.properties.generated.TextAnchor
import com.mapbox.maps.extension.style.layers.properties.generated.TextJustify
import com.mapbox.maps.plugin.animation.MapAnimationOptions


@OptIn(MapboxExperimental::class)
@Composable
fun MapScreenWithMarkers(
    currentLocation: Point,
    originLocation: Point,
    modifier: Modifier = Modifier,
    isDestination: Boolean = false,
    drivers: List<Driver> = emptyList(),
    directions: List<Point> = emptyList(),
    onClick: (driver: Driver) -> Unit,
    zoomLevel: Double = 15.0,
    @DrawableRes
    markerIconResId: Int = R.drawable.map_mark,
    onMapClicked: (Location) -> Unit = {},
) {
    val context = LocalContext.current
    var cameraCenter by remember(currentLocation) { mutableStateOf(currentLocation) }
    val cameraPositionState = rememberMapViewportState {
        setCameraOptions {
            zoom(zoomLevel)
            center(currentLocation)
            bearing(0.0)
            pitch(0.0)
        }
    }

    val mapAnimationOptions = remember { MapAnimationOptions.Builder().duration(1500L).build() }

    LaunchedEffect(key1 = currentLocation, key2 = zoomLevel, key3 = cameraCenter) {
        cameraPositionState.flyTo(
            cameraOptions = CameraOptions.Builder()
                .center(cameraCenter)
                .zoom(zoomLevel)
                .build(),
            animationOptions = mapAnimationOptions
        )
    }

    val markerIcon = remember { context.drawableToBitmap(markerIconResId) }
    val homeMarkerIcon = remember { context.drawableToBitmap(R.drawable.home_location) }

    MapboxMap(
        modifier = modifier,
        mapViewportState = cameraPositionState,
        logo = {},
        compass = {},
        scaleBar = {},
        attribution = {},
        onMapClickListener = {
            if (isDestination)
                onMapClicked(it.toLocation())
            isDestination
        }
    ) {

        PointAnnotation(
            iconImageBitmap = homeMarkerIcon,
            iconSize = 1.75,
            point = originLocation,
        )

        if (isDestination && directions.isNotEmpty()) {
            PolylineAnnotation(
                points = directions,
                lineWidth = 5.0,
                lineBorderWidth = 5.0,
                lineBorderColorInt = MafraqTheme.colors.primary.toArgb()
            )

            cameraCenter = directions.middle(before = 1)

            PointAnnotation(
                iconImageBitmap = markerIcon,
                iconSize = 1.3,
                point = directions.last(),
            )
        }

        drivers.forEach { driver ->
            val point = driver.location.toPoint()
            PointAnnotation(
                iconImageBitmap = markerIcon,
                iconSize = 1.3,
                textSize = 11.5,
                point = point,
                textField = driver.carName,
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
