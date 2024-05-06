package com.mafraq.data.entities


sealed interface AppUserType {
    data object Driver : AppUserType
    data object Employee : AppUserType

    val isDriverApp: Boolean
        get() = this is Driver

    val isEmployeeApp: Boolean
        get() = this is Employee
}
