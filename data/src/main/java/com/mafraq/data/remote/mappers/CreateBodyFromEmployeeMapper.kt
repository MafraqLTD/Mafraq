package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.altaie.prettycode.core.utils.extenstions.toJson
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.remote.models.InsertRowBody
import com.mafraq.data.utils.generateRandomId
import com.mafraq.data.utils.serializedWithSeparator
import java.util.UUID
import javax.inject.Inject


class CreateBodyFromEmployeeMapper @Inject constructor(

) : Mapper<Employee, InsertRowBody> {

    override fun map(from: Employee): InsertRowBody = from.run {
        val values: Map<String, String> = mapOf(
            "itOGYf6QL92zUsCR" to birthday,
            "cm6TKIRbdzcwUOLt" to offDays.serializedWithSeparator { it.name },
            "3NugnVDLDBCDst4x" to email,
            "uwsRtDxOv83FsWPL" to String.generateRandomId(),
            "MFSzAjyB5cKPffWC" to driverId,
            "geU4o1VQrUH0gJLO" to fullName,
            "HKT8vh8KsERfeWvN" to gender,
            "bn1RfhqUM27zkACQ" to "${homeLocation.latitude},${homeLocation.longitude}",
            "kD9MZqEmmDd9dFVE" to "${workLocation.latitude},${workLocation.longitude}",
            "v51SdBH4T4HvTLaL" to phone,
            "Nd4WH9qQzuZL0qza" to profilePicture,
            "DT13E8TN2406YACQ" to subscriptionStatus.name
        )

        InsertRowBody.createFromMap(values = values)
    }

}
