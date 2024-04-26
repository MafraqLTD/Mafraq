package com.mafraq.presentation.features.search


interface SearchEvent {
    object NavigateBack : SearchEvent
    object NavigateToMap : SearchEvent
}
