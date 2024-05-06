package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.entities.profile.GeneralProfile
import com.mafraq.data.entities.profile.EmployeeSubscriptionStatus
import com.mafraq.data.local.profile.ProfileLocalDataSource
import com.mafraq.data.utils.toFormattedString
import javax.inject.Inject


class EmployeeFromGeneralProfileMapper @Inject constructor(
    private val profileLocalDataSource: ProfileLocalDataSource<Employee>
) : Mapper<GeneralProfile, Employee> {
    override fun map(from: GeneralProfile): Employee = from.run {
        val employee = profileLocalDataSource.get() ?: Employee()
        employee.copy(
            email = email,
            rowId = rowId,
            workLocation = workLocation,
            homeLocation = homeLocation,
            phone = phone,
            fullName = fullName,
            birthday = birthday?.toFormattedString().orEmpty(),
            offDays = offDays.toList(),
            gender = gender,
            profilePictureUrl = profilePictureUrl,
            subscriptionStatus = if (employee.driverEmail.isEmpty())
                EmployeeSubscriptionStatus.Inactive
            else
                EmployeeSubscriptionStatus.Active
        )
    }
}
