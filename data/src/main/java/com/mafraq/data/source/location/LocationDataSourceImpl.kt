package com.mafraq.data.source.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import com.mafraq.data.entities.map.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.altaie.gls.GLSManager
import com.altaie.gls.domain.entities.Priority
import com.altaie.prettycode.core.exceptions.GpsProviderIsDisabledException
import com.mafraq.data.utils.locationManager
import com.mafraq.data.utils.toDomain
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.timeout
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds


class LocationDataSourceImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val glsManager: GLSManager,
) : LocationDataSource {

    override val isLocationEnabled: Boolean
        get() = context.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    override val isLocationSettingSatisfied: Boolean
        get() = isLocationPermissionGranted && isLocationEnabled

    override val isLocationPermissionGranted: Boolean
        get() = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    override suspend fun getLastLocation(): Location =
        glsManager.lastLocation().toData?.toDomain() ?: Location()

    @OptIn(FlowPreview::class)
    override fun requestLocationUpdates(maxUpdates: Int): Flow<Location> {
        glsManager.configureLocationRequest(
            priority = Priority.HighAccuracy,
            maxUpdates = maxUpdates
        )
        return glsManager.requestLocationUpdatesAsFlow()
            .mapNotNull {
                it.toError?.run { throw GpsProviderIsDisabledException(message, code) }
                it.toData?.toDomain()
            }
            .take(maxUpdates)
            .timeout((maxUpdates * 2).seconds)
    }

    override fun removeLocationUpdates() = glsManager.removeLocationUpdates()
}
