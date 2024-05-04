package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.entities.profile.GeneralProfile
import com.mafraq.data.entities.profile.EmployeeSubscriptionStatus
import com.mafraq.data.local.profile.ProfileLocalDataSource
import com.mafraq.data.utils.toFormattedString
import com.mafraq.data.utils.toLocalDate
import javax.inject.Inject


class GeneralProfileFromEmployeeMapper @Inject constructor(

) : Mapper<Employee, GeneralProfile> {
    override fun map(from: Employee):  GeneralProfile= from.run {
        GeneralProfile(
            email = email,
            rowId = rowId,
            workLocation = workLocation,
            homeLocation = homeLocation,
            phone = phone,
            fullName = fullName,
            birthday = birthday.toLocalDate("yyyy-MM-dd"),
            offDays = offDays.toSet(),
            gender = gender,
            profilePictureUrl = profilePictureUrl,
        )
    }
}
