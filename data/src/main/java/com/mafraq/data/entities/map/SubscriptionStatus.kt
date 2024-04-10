package com.mafraq.data.entities.map

import com.mafraq.data.utils.pascalcase


enum class SubscriptionStatus {
    Full,
    NeedMore,
    Empty;

    companion object {
        fun fromString(value: String?): SubscriptionStatus =
            when (value?.pascalcase()?.replace(" ", "")) {
                Full.name -> Full
                NeedMore.name -> NeedMore
                Empty.name -> Empty
                else -> throw IllegalArgumentException("Unknown value: $value")
            }
    }
}
