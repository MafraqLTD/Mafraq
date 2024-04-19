package com.mafraq.presentation.utils.extensions

import com.google.android.gms.maps.model.LatLng
import com.mafraq.data.entities.map.Location
import com.mapbox.geojson.Point


fun Location.toLatLng() = LatLng(latitude, longitude)

fun LatLng.toPoint(): Point? = Point.fromLngLat(this.longitude, this.latitude)
fun Location.toPoint(): Point = Point.fromLngLat(this.longitude, this.latitude)
fun Point.toLocation(): Location = Location(
    latitude = latitude(),
    longitude = longitude()
)
