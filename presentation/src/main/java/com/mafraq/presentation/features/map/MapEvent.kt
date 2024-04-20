package com.mafraq.presentation.features.map


sealed interface MapEvent {
    data object OnNavigateBack : MapEvent
}
