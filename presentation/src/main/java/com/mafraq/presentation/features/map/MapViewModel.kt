package com.mafraq.presentation.features.map

import androidx.lifecycle.SavedStateHandle
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.map.Location
import com.mafraq.data.repository.crm.CRMRepository
import com.mafraq.data.repository.hardware.HardwareRepository
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.navigation.MapScreenArgs
import com.mafraq.presentation.utils.location.LocationSettingsDelegate
import com.mafraq.presentation.utils.location.LocationSettingsDelegateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MapViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val crmRepository: CRMRepository,
    private val hardwareRepository: HardwareRepository,
    private val locationSettingsDelegate: LocationSettingsDelegateImpl,
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

    override fun onMapClicked(location: Location) =
        updateState { copy(destinationLocation = location) }

    override fun onConfirmDestination() {
        TODO(
            "SEND THE DATA TO RETABLE" +
                "THEN IF SUCCESSFULLY NAVIGATE BACK"
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
                    updateState { copy(currentLocation = it) }
                },
                onCompleted = {
                    hardwareRepository.removeLocationUpdates()
                }
            )
    }

    override fun cancelLocationUpdates() = hardwareRepository.removeLocationUpdates()


    private fun initialize() {
        if (args.latitude != null && args.longitude != null) {
            updateState {
                copy(
                    isDestination = true,
                    destinationLocation = Location(
                        args.latitude?.toDouble()!!,
                        args.longitude?.toDouble()!!
                    )
                )
            }
        }

        tryToExecute(
            block = { crmRepository.getDrivers() },
            onSuccess = {
                updateState { copy(availableDrivers = it) }
            },
        )
    }
}
