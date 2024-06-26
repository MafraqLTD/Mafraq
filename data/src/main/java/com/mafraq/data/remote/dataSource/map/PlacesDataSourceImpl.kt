package com.mafraq.data.remote.dataSource.map

import android.location.Address
import android.location.Geocoder
import android.os.Build
import com.altaie.prettycode.core.base.BaseRemoteDataSource
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.ktx.api.net.awaitFindAutocompletePredictions
import com.mafraq.data.entities.map.Directions
import com.mafraq.data.entities.map.Location
import com.mafraq.data.entities.map.PlaceSuggestion
import com.mafraq.data.entities.map.PlaceSuggestionWithCoordinate
import com.mafraq.data.remote.mappers.PlaceSuggestionMapper
import com.mafraq.data.remote.mappers.fromMapBoxLocation
import com.mafraq.data.remote.mappers.toDirections
import com.mafraq.data.remote.mappers.toRouteBody
import com.mafraq.data.remote.models.routes.request.RouteDirectionsBody
import com.mafraq.data.remote.service.DirectionsApiService
import com.mafraq.data.repository.hardware.HardwareRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


@OptIn(ExperimentalCoroutinesApi::class)
class PlacesDataSourceImpl @Inject constructor(
    private val geocoder: Geocoder,
    private val placesClient: PlacesClient,
    private val autocompleteSessionToken: AutocompleteSessionToken,
    private val placeSuggestionMapper: PlaceSuggestionMapper,
    private val hardwareRepository: HardwareRepository,
    private val directionsApiService: DirectionsApiService,
) : PlacesDataSource, BaseRemoteDataSource {

    override suspend fun getPlaceSuggestions(query: String): List<PlaceSuggestion> {
        val lastLocation = hardwareRepository.getLastLocation()
        val response = placesClient.awaitFindAutocompletePredictions {
            setQuery(query)
            setCountries(IRAQ_ISO_CODE)
            sessionToken = autocompleteSessionToken
            origin = LatLng(lastLocation.latitude, lastLocation.longitude)
        }.autocompletePredictions
        return placeSuggestionMapper.mapList(response)
    }

    override suspend fun selectSuggestedPlace(
        placeSuggestion: PlaceSuggestion
    ): PlaceSuggestionWithCoordinate {
        val address: Address = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            suspendCoroutine { continuation ->
                geocoder.getFromLocationName(placeSuggestion.name, 1) { result ->
                    result.firstOrNull()?.let(continuation::resume)
                        ?: continuation.resumeWithException(Exception("No address found"))
                }
            }
        else
            geocoder.getFromLocationName(placeSuggestion.name, 1)!!.first()

        return PlaceSuggestionWithCoordinate(
            name = placeSuggestion.name,
            formattedAddress = placeSuggestion.formattedAddress,
            location = Location(
                latitude = address.latitude,
                longitude = address.longitude
            )
        )
    }

    override suspend fun getDirections(
        originLocation: Location,
        destinationLocation: Location
    ): Directions = apiCall(
        suspendFunction = {
            directionsApiService.computeRoutes(
                RouteDirectionsBody(
                    origin = originLocation.toRouteBody(),
                    destination = destinationLocation.toRouteBody(),
                )
            )
        },
        mapper = { it.routes.toDirections() }
    ).toData ?: Directions.empty

    // TODO ( USE MAPBOX TO GET THE INFORMATION )
    override suspend fun getLocationInfo(
        latitude: Double,
        longitude: Double
    ): Location {
        val location = Location(longitude = longitude, latitude = latitude).fromMapBoxLocation()
        return apiCall(
            suspendFunction = {
                directionsApiService.getLocationInfo(
                    lat = location.latitude,
                    lng = location.longitude
                )
            },
            mapper = {
                val result = it.results?.firstOrNull()

                Location(
                    latitude = location.latitude,
                    longitude = location.longitude,
                    governorate = result?.state.orEmpty(),
                    city = result?.city.orEmpty(),
                    district = result?.suburb.orEmpty(),
                )
            }
        ).toData ?: Location()
    }

    companion object {
        private const val IRAQ_ISO_CODE = "IQ"
    }

}
