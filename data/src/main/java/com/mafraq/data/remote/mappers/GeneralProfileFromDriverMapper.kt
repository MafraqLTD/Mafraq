package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.entities.profile.GeneralProfile
import com.mafraq.data.entities.profile.EmployeeSubscriptionStatus
import com.mafraq.data.local.profile.ProfileLocalDataSource
import com.mafraq.data.utils.toFormattedString
import com.mafraq.data.utils.toLocalDate
import javax.inject.Inject


class GeneralProfileFromDriverMapper @Inject constructor(

) : Mapper<Driver, GeneralProfile> {
    override fun map(from: Driver):  GeneralProfile= from.run {
        GeneralProfile(
            email = email,
            rowId = rowId,
            homeLocation = location,
            phone = phone,
            fullName = fullName,
            birthday = birthday.toLocalDate("yyyy-MM-dd"),
            gender = gender,
            profilePictureUrl = profilePictureUrl,
        )
    }
}
