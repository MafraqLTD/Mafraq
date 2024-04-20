package com.mafraq.presentation.navigation.arguments

import androidx.lifecycle.SavedStateHandle
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.getArgOrNull


class ProfileScreenArgs(savedStateHandle: SavedStateHandle) {
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

    var addressId: Int? = savedStateHandle.getArgOrNull(Screen.Map.ADDRESS_ID_ARG)
}
