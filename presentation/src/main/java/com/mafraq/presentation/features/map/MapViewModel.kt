package com.mafraq.presentation.features.map

import com.mafraq.data.entities.map.Driver
import com.mafraq.presentation.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MapViewModel @Inject constructor(

) : BaseViewModel<MapUiState, MapEvent>(MapUiState()),
    MapInteractionListener {
    override fun onNavigateBack() {
        emitNewEvent(MapEvent.OnNavigateBack)
    }

    override fun onDriverMarkClick(driver: Driver) {
        // TODO("Not yet implemented")
    }

}
