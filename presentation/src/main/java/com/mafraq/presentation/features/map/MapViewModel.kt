package com.mafraq.presentation.features.map

import com.mafraq.data.entities.map.Driver
import com.mafraq.data.repository.hardware.HardwareRepository
import com.mafraq.presentation.features.base.BaseViewModel
import com.mafraq.presentation.utils.location.LocationSettingsDelegate
import com.mafraq.presentation.utils.location.LocationSettingsDelegateImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MapViewModel @Inject constructor(
    private val hardwareRepository: HardwareRepository,
    private val locationSettingsDelegate: LocationSettingsDelegateImpl
) : BaseViewModel<MapUiState, MapEvent>(MapUiState()),
    MapInteractionListener, LocationSettingsDelegate by locationSettingsDelegate {

    override fun onNavigateBack() {
        emitNewEvent(MapEvent.OnNavigateBack)
    }

    override fun onPermissionGranted() = updateState {
        copy(isPermissionGranted = true).also {
            updateLocation()
        }
    }

    override fun onDriverMarkClick(driver: Driver) {
        // TODO("Not yet implemented")
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

}
