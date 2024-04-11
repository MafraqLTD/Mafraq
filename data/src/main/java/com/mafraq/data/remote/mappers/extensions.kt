package com.mafraq.data.remote.mappers

import com.mafraq.data.entities.Subscriber
import com.mafraq.data.entities.map.Location
import com.mafraq.data.remote.models.DriverRemote
import com.mafraq.data.remote.models.LocationRemote
import com.mafraq.data.utils.mapToListOf


fun LocationRemote?.toDomain() = Location(
    latitude = this?.latitude ?: 0.0,
    longitude = this?.longitude ?: 0.0
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
