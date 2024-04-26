package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.altaie.prettycode.core.utils.extenstions.toJson
import com.google.android.play.integrity.internal.f
import com.mafraq.data.entities.map.Location
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.local.profile.ProfileLocalDataSourceImpl
import com.mafraq.data.remote.models.InsertRowBody
import com.mafraq.data.remote.models.UpdateRowBody
import com.mafraq.data.utils.serializedWithSeparator
import javax.inject.Inject


class UpdateBodyFromEmployeeMapper @Inject constructor(
    private val profileLocalDataSource: ProfileLocalDataSourceImpl
) : Mapper<Employee, UpdateRowBody> {

    override fun map(from: Employee): UpdateRowBody = from.run {
        val savedValue = profileLocalDataSource.get()
        val values: Map<String, String> = mapOf(
            "itOGYf6QL92zUsCR" to savedValue?.birthday.updatedOrEmpty(birthday),
            "cm6TKIRbdzcwUOLt" to offDays.serializedWithSeparator { it.name },
            "3NugnVDLDBCDst4x" to savedValue?.email.updatedOrEmpty(email),
//            "uwsRtDxOv83FsWPL" to email, // ID
            "MFSzAjyB5cKPffWC" to savedValue?.driverId.updatedOrEmpty(driverId),
            "geU4o1VQrUH0gJLO" to savedValue?.fullName.updatedOrEmpty(fullName),
            "HKT8vh8KsERfeWvN" to savedValue?.gender.updatedOrEmpty(gender),
            "bn1RfhqUM27zkACQ" to savedValue?.homeLocation?.latLng.updatedOrEmpty(homeLocation.latLng),
            "kD9MZqEmmDd9dFVE" to savedValue?.workLocation?.latLng.updatedOrEmpty(workLocation.latLng),
            "v51SdBH4T4HvTLaL" to savedValue?.phone.updatedOrEmpty(phone),
            "Nd4WH9qQzuZL0qza" to savedValue?.profilePicture.updatedOrEmpty(profilePicture),
            "DT13E8TN2406YACQ" to savedValue?.subscriptionStatus?.name.updatedOrEmpty(
                subscriptionStatus.name
            )
        ).filter { it.value.isNotEmpty() }

        val rowId = profileLocalDataSource.get()?.rowId ?: rowId
        UpdateRowBody.updateFromMap(values = values, rowId = rowId)
    }

    private fun <T> T?.updatedOrEmpty(value: T): String {
        return if (this != value)
            value.toString()
        else
            ""
    }
}
