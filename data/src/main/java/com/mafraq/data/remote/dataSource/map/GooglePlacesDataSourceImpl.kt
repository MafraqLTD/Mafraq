package com.mafraq.data.remote.dataSource.map

import android.location.Address
import android.location.Geocoder
import android.os.Build
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.ktx.api.net.awaitFindAutocompletePredictions
import com.mafraq.data.entities.map.Location
import com.mafraq.data.entities.map.PlaceSuggestion
import com.mafraq.data.entities.map.PlaceSuggestionWithCoordinate
import com.mafraq.data.remote.mappers.PlaceSuggestionMapper
import com.mafraq.data.repository.hardware.HardwareRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


@OptIn(ExperimentalCoroutinesApi::class)
class GooglePlacesDataSourceImpl @Inject constructor(
    private val geocoder: Geocoder,
    private val placesClient: PlacesClient,
    private val autocompleteSessionToken: AutocompleteSessionToken,
    private val placeSuggestionMapper: PlaceSuggestionMapper,
    private val hardwareRepository: HardwareRepository,
) : GooglePlacesDataSource {

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
                    continuation.resume(result.first())
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

    companion object {
        private const val IRAQ_ISO_CODE = "IQ"
    }

}
