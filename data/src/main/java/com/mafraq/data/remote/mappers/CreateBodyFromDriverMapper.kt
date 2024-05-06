package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.remote.models.InsertRowBody
import javax.inject.Inject


class CreateBodyFromDriverMapper @Inject constructor(

) : Mapper<Driver, InsertRowBody> {

    override fun map(from: Driver): InsertRowBody = from.run {
        val values: Map<String, String> = mapOf(
            "itOGYf6QL92zUsCR" to birthday,
            "3NugnVDLDBCDst4x" to email,
            "uwsRtDxOv83FsWPL" to email, // ID
            "geU4o1VQrUH0gJLO" to fullName,
            "HKT8vh8KsERfeWvN" to gender,
            "EnY7e3a6nqPkD2Hx" to carName,
            "nHvidoqOQpx6wpkv" to carNumber,
            "OStv8U33gbXj7gCV" to snippet,
            "lzjBcXixOx7zMisx" to nationalId,
            "kD9MZqEmmDd9dFVE" to "${location.latitude},${location.longitude}",
            "v51SdBH4T4HvTLaL" to phone,
            "Nd4WH9qQzuZL0qza" to profilePictureUrl,
            "DT13E8TN2406YACQ" to subscriptionStatus.name
        )

        InsertRowBody.createFromMap(values = values)
    }

}
