package com.mafraq.presentation.features.map

import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.map.Location
import com.mafraq.presentation.utils.location.LocationSettingsDelegate


interface MapInteractionListener : LocationSettingsDelegate {

    fun updateLocation()
    fun cancelLocationUpdates()
    fun onNavigateBack()
    fun onPermissionGranted()
    fun onDismissDriverDetails()
    fun onDriverMarkClick(driver: Driver)
    fun onMapClicked(location: Location)
    fun onConfirmDestination()
    fun onSubscriptionRequest()

    object Preview : MapInteractionListener,
        LocationSettingsDelegate by LocationSettingsDelegate.DummyImpl {
        override fun updateLocation() = Unit
        override fun cancelLocationUpdates() = Unit
        override fun onNavigateBack() = Unit
        override fun onPermissionGranted() = Unit
        override fun onSubscriptionRequest() = Unit
        override fun onDismissDriverDetails() = Unit
        override fun onDriverMarkClick(driver: Driver) = Unit
        override fun onMapClicked(location: Location) = Unit
        override fun onConfirmDestination() = Unit

    }
}
