package com.mafraq.presentation.features.map

import com.mafraq.data.entities.map.Driver


interface MapInteractionListener {

    fun onNavigateBack()
    fun onDriverMarkClick(driver: Driver)

    object Preview : MapInteractionListener {
        override fun onNavigateBack() = Unit
        override fun onDriverMarkClick(driver: Driver) = Unit

    }
}
