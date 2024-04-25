package com.mafraq.data.entities.profile

import com.mafraq.data.utils.titlecase


enum class EmployeeSubscriptionStatus {
    Active,
    Inactive;

    companion object {
        fun fromString(value: String?): EmployeeSubscriptionStatus =
            when (value?.titlecase()) {
                Active.name -> Active
                Inactive.name -> Inactive
                else -> throw IllegalArgumentException("Unknown value: $value")
            }
    }
}
