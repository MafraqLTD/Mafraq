package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.local.profile.DriverProfileLocalDataSourceImpl
import com.mafraq.data.remote.models.UpdateRowBody
import javax.inject.Inject


class UpdateBodyFromDriverMapper @Inject constructor(
    private val profileLocalDataSource: DriverProfileLocalDataSourceImpl
) : Mapper<Driver, UpdateRowBody> {

    override fun map(from: Driver): UpdateRowBody = from.run {
        val savedValue = profileLocalDataSource.get()
        val values: Map<String, String> = mapOf(
            "itOGYf6QL92zUsCR" to savedValue?.birthday.updatedOrEmpty(birthday),
            "3NugnVDLDBCDst4x" to savedValue?.email.updatedOrEmpty(email),
            "geU4o1VQrUH0gJLO" to savedValue?.fullName.updatedOrEmpty(fullName),
            "HKT8vh8KsERfeWvN" to savedValue?.gender.updatedOrEmpty(gender),
            "kD9MZqEmmDd9dFVE" to savedValue?.location?.latLng.updatedOrEmpty(location.latLng),
            "v51SdBH4T4HvTLaL" to savedValue?.phone.updatedOrEmpty(phone),
            "Nd4WH9qQzuZL0qza" to savedValue?.profilePictureUrl.updatedOrEmpty(profilePictureUrl),
            "DT13E8TN2406YACQ" to savedValue?.subscriptionStatus?.name.updatedOrEmpty(
                subscriptionStatus.name
            ),
            "EnY7e3a6nqPkD2Hx" to savedValue?.carName.updatedOrEmpty(carName),
            "nHvidoqOQpx6wpkv" to savedValue?.carNumber.updatedOrEmpty(carNumber),
            "OStv8U33gbXj7gCV" to savedValue?.snippet.updatedOrEmpty(snippet),
            "lzjBcXixOx7zMisx" to savedValue?.nationalId.updatedOrEmpty(nationalId),
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
