package com.mafraq.presentation.features.map

import com.mafraq.data.entities.map.Driver
import com.mafraq.presentation.utils.location.LocationSettingsDelegate


interface MapInteractionListener : LocationSettingsDelegate {

    fun updateLocation()
    fun cancelLocationUpdates()
    fun onNavigateBack()
    fun onPermissionGranted()
    fun onDriverMarkClick(driver: Driver)

    object Preview : MapInteractionListener,
        LocationSettingsDelegate by LocationSettingsDelegate.DummyImpl {
        override fun updateLocation() = Unit
        override fun cancelLocationUpdates() = Unit
        override fun onNavigateBack() = Unit
        override fun onPermissionGranted() = Unit
        override fun onDriverMarkClick(driver: Driver) = Unit

    }
}
