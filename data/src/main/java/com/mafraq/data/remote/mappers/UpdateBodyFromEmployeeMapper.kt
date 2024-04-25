package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.altaie.prettycode.core.utils.extenstions.toJson
import com.mafraq.data.entities.map.Location
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.remote.models.InsertRowBody
import com.mafraq.data.utils.serializedWithSeparator
import javax.inject.Inject


class UpdateBodyFromEmployeeMapper @Inject constructor(

) : Mapper<Employee, InsertRowBody> {

    override fun map(from: Employee): InsertRowBody = from.run {
        val values: Map<String, String> = mapOf(
            "Birthday" to birthday,
            "Day off" to offDays.serializedWithSeparator { it.name },
            "Email" to email,
            "Employee ID" to id,
            "Driver ID" to driverId,
            "Full Name" to fullName,
            "Gender" to gender,
            "Home Location" to homeLocation.serializedOrEmpty(),
            "Work Location" to workLocation.serializedOrEmpty(),
            "Phone" to phone,
            "Profile Picture" to profilePicture,
            "Subscription Status" to subscriptionStatus.name
        ).filter { it.value.isNotEmpty() }

        InsertRowBody.updateFromMap(values = values)
    }

    private fun Location.serializedOrEmpty(): String {
        if (latitude == 0.0 || longitude == 0.0)
            return ""
        return toJson()
    }
}
