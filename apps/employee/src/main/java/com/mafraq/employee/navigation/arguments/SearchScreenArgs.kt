package com.mafraq.employee.navigation.arguments

import androidx.lifecycle.SavedStateHandle
import com.altaie.prettycode.core.utils.extenstions.isTrue
import com.mafraq.presentation.navigation.Screen
import com.mafraq.presentation.utils.extensions.getArgOrNull


class SearchScreenArgs(savedStateHandle: SavedStateHandle) {
    var isFromProfile: Boolean = savedStateHandle
        .getArgOrNull<Boolean?>(Screen.Map.IS_FROM_PROFILE_ARG).isTrue
}
