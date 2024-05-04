package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.profile.GeneralProfile
import com.mafraq.data.local.profile.ProfileLocalDataSource
import com.mafraq.data.utils.toFormattedString
import javax.inject.Inject


class DriverFromGeneralProfileMapper @Inject constructor(
    private val profileLocalDataSource: ProfileLocalDataSource<Driver>
) : Mapper<GeneralProfile, Driver> {
    override fun map(from: GeneralProfile): Driver = from.run {
        val driver = profileLocalDataSource.get() ?: Driver()
        driver.copy(
            email = email,
            rowId = rowId,
            location = homeLocation,
            phone = phone,
            fullName = fullName,
            birthday = birthday?.toFormattedString().orEmpty(),
            gender = gender,
            carName = car,
            carNumber = carNumber,
            nationalId = nationalId,
            snippet = snippet,
            profilePictureUrl = profilePictureUrl
        )
    }
}
