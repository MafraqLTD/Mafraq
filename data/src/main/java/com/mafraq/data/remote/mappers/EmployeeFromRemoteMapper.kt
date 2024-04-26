package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.MapperList
import com.mafraq.data.entities.profile.DayOff
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.entities.profile.EmployeeSubscriptionStatus
import com.mafraq.data.remote.models.ApiResponseRemote
import com.mafraq.data.remote.models.EmployeeRemote
import com.mafraq.data.utils.toFormattedString
import com.mafraq.data.utils.toLocalDate
import javax.inject.Inject


class EmployeeFromRemoteMapper @Inject constructor() :
    MapperList<ApiResponseRemote.RowRemote<EmployeeRemote>, Employee> {

    override fun map(from: ApiResponseRemote.RowRemote<EmployeeRemote>) = from.item.run {
        Employee(
            rowId = from.rowId,
            birthday = birthday?.toLocalDate()?.toFormattedString("yyyy-MM-dd").orEmpty(),
            email = email.orEmpty(),
            fullName = fullName.orEmpty(),
            id = employeeID.orEmpty(),
            driverId = driverId.orEmpty(),
            homeLocation = homeLocation.toDomain(),
            workLocation = workLocation.toDomain(),
            phone = phone.orEmpty(),
            gender = gender.orEmpty(),
            profilePicture = profilePicture.orEmpty(),
            offDays = offDays?.value?.map(DayOff::fromString).orEmpty(),
            subscriptionStatus = EmployeeSubscriptionStatus.fromString(subscriptionStatus)
        )
    }

    override fun mapList(from: List<ApiResponseRemote.RowRemote<EmployeeRemote>>): List<Employee> =
        from.map(::map)
}
