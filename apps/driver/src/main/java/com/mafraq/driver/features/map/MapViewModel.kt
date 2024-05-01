package com.mafraq.driver.features.map

import androidx.lifecycle.SavedStateHandle
import com.mafraq.data.entities.map.Location
import com.mafraq.data.remote.mappers.toPoint
import com.mafraq.data.repository.crm.CRMRepository
import com.mafraq.data.repository.hardware.HardwareRepository
import com.mafraq.data.repository.map.MapPlacesRepository
import com.mafraq.data.repository.subscription.driver.DriverSubscriptionRepository
import com.mafraq.driver.navigation.arguments.MapScreenArgs
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.utils.location.LocationSettingsDelegate
import com.mafraq.presentation.utils.location.LocationSettingsDelegateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MapViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val crmRepository: CRMRepository,
    private val placesRepository: MapPlacesRepository,
    private val hardwareRepository: HardwareRepository,
    private val locationSettingsDelegate: LocationSettingsDelegateImpl,
    private val driverSubscriptionRepository: DriverSubscriptionRepository
) : BaseViewModel<MapUiState, MapEvent>(MapUiState()),
    MapInteractionListener, LocationSettingsDelegate by locationSettingsDelegate {

    private val args by lazy {
        MapScreenArgs(
            savedStateHandle
        )
    }

    init {
        initialize()
    }

    override fun onNavigateBack() {
        emitNewEvent(MapEvent.OnNavigateBack)
    }

    override fun onPermissionGranted() = updateState {
        copy(isPermissionGranted = true).also {
            updateLocation()
        }
    }

    override fun onMapClicked(location: Location) {
        updateState { copy(currentLocation = location) }
        findBestRoute()
    }

    override fun onConfirmDestination() {
        val destination = state.value.destinationLocation
         emitNewEvent(
                MapEvent.OnNavigateToProfile(
                    latitude = destination.latitude,
                    longitude = destination.longitude
                )
            )
    }

    override fun acceptSubscribeRequest() {
        tryToExecute(
            block = { driverSubscriptionRepository.accept() },
            onSuccess = { },
            onError = { }
        )
    }

    override fun updateLocation() {
        if (hardwareRepository.isLocationSettingSatisfied)
            tryToCollect(
                block = hardwareRepository::requestLocationUpdates,
                onNewValue = {
                    updateState { copy(currentLocation = it) }
                },
                onCompleted = {
                    hardwareRepository.removeLocationUpdates()
                    findBestRoute()
                }
            )
    }

    private fun findBestRoute() {
        if (state.value.isViewOnly.not()) return

        updateState { copy(isLoading = true) }

        tryToExecute(
            block = {
                placesRepository.getDirections(
                    originLocation = state.value.currentLocation,
                    destinationLocation = state.value.destinationLocation
                )
            },
            onSuccess = {
                updateState {
                    copy(
                        directions = it.locationPoints.map(Location::toPoint),
                        directionsZoomLevel = it.zoomLevel
                    )
                }
            },
            onCompleted = {
                updateState { copy(isLoading = false) }
            }
        )
    }

    override fun cancelLocationUpdates() = hardwareRepository.removeLocationUpdates()

    private fun initialize() {
        if (args.latitude != null && args.longitude != null) {
            updateState {
                copy(
                    isViewOnly = true,
                    isLoading = true,
                    destinationLocation = Location(
                        args.latitude?.toDouble()!!,
                        args.longitude?.toDouble()!!
                    )
                )
            }
        }
    }
}
