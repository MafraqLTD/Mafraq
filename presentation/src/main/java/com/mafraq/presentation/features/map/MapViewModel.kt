package com.mafraq.presentation.features.map

import com.mafraq.data.entities.map.Driver
import com.mafraq.data.repository.hardware.HardwareRepository
import com.mafraq.presentation.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MapViewModel @Inject constructor(
    private val hardwareRepository: HardwareRepository
) : BaseViewModel<MapUiState, MapEvent>(MapUiState()),
    MapInteractionListener {

    init {
        updateLocation()
    }
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

    private fun updateLocation() {
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

}
