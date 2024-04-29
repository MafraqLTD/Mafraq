package com.mafraq.employee.features.search


interface SearchEvent {
    object NavigateBack : SearchEvent
    object NavigateToMap : SearchEvent
}
