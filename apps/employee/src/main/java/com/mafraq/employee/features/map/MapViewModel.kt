package com.mafraq.employee.features.map

import androidx.lifecycle.SavedStateHandle
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.map.Location
import com.mafraq.data.entities.profile.GeneralProfile
import com.mafraq.data.remote.mappers.toPoint
import com.mafraq.data.repository.crm.CRMRepository
import com.mafraq.data.repository.hardware.HardwareRepository
import com.mafraq.data.repository.map.MapPlacesRepository
import com.mafraq.data.repository.subscription.employee.EmployeeSubscriptionRepository
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.features.profile.ProfileEvent
import com.mafraq.employee.navigation.arguments.MapScreenArgs
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
    private val employeeSubscriptionRepository: EmployeeSubscriptionRepository,
) : BaseViewModel<MapUiState, MapEvent>(MapUiState()),
    MapInteractionListener, LocationSettingsDelegate by locationSettingsDelegate {

    private val args by lazy { MapScreenArgs(savedStateHandle) }

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

    override fun onDriverMarkClick(driver: Driver) = updateState {
        copy(selectedDriver = driver, showDriverDetails = true)
    }

    override fun onMapClicked(location: Location) {
        updateState { copy(destinationLocation = location) }
        findBestRoute()
    }

    override fun onConfirmDestination() {
        val destination = state.value.destinationLocation

        if (args.isFromProfile)
            emitNewEvent(
                MapEvent.OnNavigateToProfile(
                    latitude = destination.latitude,
                    longitude = destination.longitude,
                    addressId = requireNotNull(args.addressId)
                )
            )
        else
            tryToExecute(
                block = {
                    crmRepository.saveEmployeeProfile(GeneralProfile(workLocation = destination))
                },
                onSuccess = {
                    emitNewEvent(MapEvent.OnNavigateBack)
                },
                onError = {
                    // TODO( "SHOW ERROR MESSAGE" )
                },
            )
    }

    override fun onSubscriptionRequest() {
        val driver = state.value.selectedDriver
        tryToExecute(
            block = { employeeSubscriptionRepository.request(driver) },
            onSuccess = { emitNewEvent(MapEvent.OnNavigateBack) },
            onError = {
                // TODO( "SHOW ERROR MESSAGE" )
            }
        )
    }

    override fun onDismissDriverDetails() = updateState {
        copy(showDriverDetails = false)
    }

    override fun updateLocation() {
        if (hardwareRepository.isLocationSettingSatisfied)
            tryToCollect(
                block = hardwareRepository::requestLocationUpdates,
                onNewValue = {
                    updateState { copy(originLocation = it) }
                },
                onCompleted = {
                    hardwareRepository.removeLocationUpdates()
                    findBestRoute()
                }
            )
    }

    private fun findBestRoute() {
        if (state.value.isDestination.not()) return

        updateState { copy(isLoading = true) }

        tryToExecute(
            block = {
                placesRepository.getDirections(
                    originLocation = state.value.originLocation,
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
                    isDestination = true,
                    isLoading = true,
                    destinationLocation = Location(
                        args.latitude?.toDouble()!!,
                        args.longitude?.toDouble()!!
                    )
                )
            }
        } else if (args.isFromProfile) {
            val isFromProfileForHomeAddress =
                args.addressId == ProfileEvent.OnNavigateToMapForHomeAddress().id

            updateState {
                copy(
                    isFromProfileForHomeAddress = isFromProfileForHomeAddress,
                    isLoading = false,
                )
            }
        } else {
            tryToExecute(
                block = { crmRepository.getDrivers() },
                onSuccess = {
                    updateState { copy(availableDrivers = it) }
                },
            )
        }
    }
}
