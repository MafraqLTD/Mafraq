package com.mafraq.driver.features.map

import com.mafraq.data.entities.map.Location
import com.mafraq.presentation.utils.location.LocationSettingsDelegate


interface MapInteractionListener : LocationSettingsDelegate {

    fun updateLocation()
    fun cancelLocationUpdates()
    fun onNavigateBack()
    fun onPermissionGranted()
    fun onMapClicked(location: Location)
    fun onConfirmDestination()
    fun acceptSubscribeRequest()

    object Preview : MapInteractionListener,
        LocationSettingsDelegate by LocationSettingsDelegate.DummyImpl {
        override fun updateLocation() = Unit
        override fun cancelLocationUpdates() = Unit
        override fun onNavigateBack() = Unit
        override fun onPermissionGranted() = Unit
        override fun onConfirmDestination() = Unit
        override fun acceptSubscribeRequest() = Unit
        override fun onMapClicked(location: Location) = Unit

    }
}
