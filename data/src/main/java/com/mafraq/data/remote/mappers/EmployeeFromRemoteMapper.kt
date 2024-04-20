package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.MapperList
import com.mafraq.data.entities.profile.DayOff
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.entities.profile.EmployeeSubscriptionStatus
import com.mafraq.data.remote.models.EmployeeRemote
import javax.inject.Inject


class EmployeeFromRemoteMapper @Inject constructor()
    : MapperList<EmployeeRemote, Employee> {

    override fun map(from: EmployeeRemote) = from.run {
        Employee(
            birthday = birthday.orEmpty(),
            email = email.orEmpty(),
            fullName = fullName.orEmpty(),
            id = employeeID.orEmpty(),
            driverId = driverId.orEmpty(),
            homeLocation = homeLocation.toDomain(),
            workLocation = workLocation.toDomain(),
            phone = phone.orEmpty(),
            gender = gender.orEmpty(),
            profilePicture = profilePicture.orEmpty(),
            dayOff = dayOff?.value?.map(DayOff::fromString).orEmpty(),
            subscriptionStatus = EmployeeSubscriptionStatus.fromString(subscriptionStatus)
        )
    }

    override fun mapList(from: List<EmployeeRemote>): List<Employee> = from.map(::map)
}
