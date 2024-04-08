package com.mafraq.data.remote.mappers

import com.mafraq.data.entities.map.Location
import com.mafraq.data.remote.models.LocationRemote


fun LocationRemote?.toDomain() = Location(
    latitude = this?.latitude ?: 0.0,
    longitude = this?.longitude ?: 0.0
)
