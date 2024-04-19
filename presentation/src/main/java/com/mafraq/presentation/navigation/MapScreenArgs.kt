package com.mafraq.presentation.navigation

import androidx.lifecycle.SavedStateHandle
import com.mafraq.presentation.utils.extensions.getArgOrNull


class MapScreenArgs(savedStateHandle: SavedStateHandle) {
    var latitude: Float? = savedStateHandle.getArgOrNull(Screen.Map.LATITUDE_ARG)
        private set
        get() = if (field == 0f)
                null
            else
                field
    var longitude: Float? = savedStateHandle.getArgOrNull(Screen.Map.LONGITUDE_ARG)
        private set
        get() = if (field == 0f)
            null
        else
            field
}
