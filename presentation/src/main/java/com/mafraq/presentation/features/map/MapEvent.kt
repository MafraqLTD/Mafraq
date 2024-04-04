package com.mafraq.presentation.features.map

import kotlin.random.Random


sealed interface MapEvent {
    data object OnNavigateBack : MapEvent
}
