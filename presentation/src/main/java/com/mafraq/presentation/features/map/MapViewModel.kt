package com.mafraq.presentation.features.map

import com.mafraq.data.entities.map.Driver
import com.mafraq.data.repository.crm.CRMRepository
import com.mafraq.data.repository.hardware.HardwareRepository
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.utils.location.LocationSettingsDelegate
import com.mafraq.presentation.utils.location.LocationSettingsDelegateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MapViewModel @Inject constructor(
    private val crmRepository: CRMRepository,
    private val hardwareRepository: HardwareRepository,
    private val locationSettingsDelegate: LocationSettingsDelegateImpl
) : BaseViewModel<MapUiState, MapEvent>(MapUiState()),
    MapInteractionListener, LocationSettingsDelegate by locationSettingsDelegate {

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

    override fun onDismissDriverDetails() = updateState {
        copy(showDriverDetails = false)
    }

    override fun updateLocation() {
        // TODO ( REMOVE RETURN LATER )
        return
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
        tryToExecute(
            block = { crmRepository.getDrivers() },
            onSuccess = {
                // TODO( REMOVE CURRENT LOCATION ASSIGNMENT LATER )
                updateState { copy(availableDrivers = it, currentLocation = it.first().location) }
            },
        )
    }
}
