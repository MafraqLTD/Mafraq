package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.entities.profile.EmployeeProfile
import com.mafraq.data.entities.profile.EmployeeSubscriptionStatus
import com.mafraq.data.local.profile.ProfileLocalDataSource
import com.mafraq.data.utils.toFormattedString
import javax.inject.Inject


class EmployeeFromEmployeeProfileMapper @Inject constructor(
    private val profileLocalDataSource: ProfileLocalDataSource
) : Mapper<EmployeeProfile, Employee> {
    override fun map(from: EmployeeProfile): Employee = from.run {
        val employee = profileLocalDataSource.get() ?: Employee()
        employee.copy(
            email = email,
            workLocation = workLocation,
            homeLocation = homeLocation,
            phone = phone,
            fullName = fullName,
            birthday = birthday.toFormattedString(),
            offDays = offDays.toList(),
            gender = gender,
            subscriptionStatus = if (employee.driverId.isEmpty())
                EmployeeSubscriptionStatus.Inactive
            else
                EmployeeSubscriptionStatus.Active
        )
    }
}
