package com.mafraq.data.source.location

import com.mafraq.data.entities.map.Location
import kotlinx.coroutines.flow.Flow


interface LocationDataSource {
    val isLocationEnabled: Boolean
    val isLocationSettingSatisfied: Boolean
    val isLocationPermissionGranted: Boolean

    suspend fun getLastLocation(): Location

    fun requestLocationUpdates(maxUpdates: Int = 4): Flow<Location>

    fun removeLocationUpdates()
}
