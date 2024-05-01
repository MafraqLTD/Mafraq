package com.mafraq.driver.navigation.arguments

import androidx.lifecycle.SavedStateHandle
import com.altaie.prettycode.core.utils.extenstions.isTrue
import com.mafraq.presentation.navigation.Screen
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

    var isFromProfile: Boolean = savedStateHandle
        .getArgOrNull<Boolean?>(Screen.Map.IS_FROM_PROFILE_ARG).isTrue
}
