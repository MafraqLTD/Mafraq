package com.mafraq.data.remote.mappers

import com.google.android.gms.maps.model.LatLng
import com.mafraq.data.entities.Subscriber
import com.mafraq.data.entities.map.Directions
import com.mafraq.data.entities.map.Location
import com.mafraq.data.remote.models.DriverRemote
import com.mafraq.data.remote.models.LocationRemote
import com.mafraq.data.remote.models.routes.request.RouteBody
import com.mafraq.data.remote.models.routes.request.RouteLocation
import com.mafraq.data.remote.models.routes.response.RouteRemote
import com.mafraq.data.remote.models.routes.response.StepRemote
import com.mafraq.data.utils.mapToListOf
import com.mapbox.geojson.Point


fun LocationRemote?.toDomain() = Location(
    latitude = this?.latitude ?: 0.0,
    longitude = this?.longitude ?: 0.0,
    city = this?.city?.replace("District", "")?.trim().orEmpty(),
    district = this?.district?.replace("District", "")?.trim().orEmpty(),
    governorate = this?.governorate?.replace("Governorate", "")?.trim().orEmpty(),
)

fun DriverRemote.toSubscribers(): List<Subscriber> {
    val ids = subscribersIds?.value.orEmpty()
    val names = subscribersName?.value.orEmpty()
    val imageUrls = subscribersPicture?.value.orEmpty()
    val locations = subscribersLocation?.value.orEmpty().mapToListOf<LocationRemote>()

    return ids.mapIndexed { index, id ->
        Subscriber(
            id = id,
            name = names[index],
            imageUrl = imageUrls[index],
            location = locations[index].toDomain(),
        )
    }
}

fun Location.toLatLng() = LatLng(latitude, longitude)

fun LatLng.toPoint(): Point? = Point.fromLngLat(this.longitude, this.latitude)
fun LatLng.toLocation(): Location = Location(latitude = latitude, longitude = longitude)
fun Location.toPoint(): Point = Point.fromLngLat(this.longitude, this.latitude)
fun Point.toLocation(): Location = Location(
    latitude = latitude(),
    longitude = longitude(),
)

fun Location.toRouteLocation(): RouteLocation = RouteLocation(latLng = toLatLng())

fun Location.toRouteBody(): RouteBody = RouteBody(location = toRouteLocation())

fun StepRemote.toLocation() = startLocation?.latLng?.toLocation()

fun List<StepRemote>?.toLocations(): List<Location> =
    this?.mapNotNull { it.toLocation() } ?: emptyList()

fun List<RouteRemote>?.firstRouteOrNull() = this?.firstOrNull()
    ?.legs
    ?.firstOrNull()

private fun List<RouteRemote>?.getLocations(): List<Location> =
    firstRouteOrNull()
        ?.steps
        ?.toLocations()
        ?: emptyList()

fun List<RouteRemote>?.toDirections(): Directions = Directions(
    locationPoints = getLocations(),
    distanceMeters = firstRouteOrNull()?.distanceMeters ?: 0,
)

fun Location.fromMapBoxLocation(): Location {
    val latitude = 3.135536248291016
    val longitude = -0.603173400878906

    return copy(latitude = this.latitude + latitude, longitude = this.longitude + longitude)
}