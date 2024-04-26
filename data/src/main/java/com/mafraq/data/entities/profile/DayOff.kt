package com.mafraq.data.entities.profile

import com.mafraq.data.utils.titlecase


enum class DayOff {
    Sunday,
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday;

    companion object {
        fun fromString(value: String?): DayOff =
            when (value?.titlecase()) {
                Sunday.name -> Sunday
                Monday.name -> Monday
                Tuesday.name -> Tuesday
                Wednesday.name -> Wednesday
                Thursday.name -> Thursday
                Friday.name -> Friday
                Saturday.name -> Saturday
                else -> throw IllegalArgumentException("Unknown value: $value")
            }
    }
}
