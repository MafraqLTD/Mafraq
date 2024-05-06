package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.mafraq.data.entities.Subscriber
import com.mafraq.data.entities.profile.Employee
import javax.inject.Inject


class SubscriberFromEmployeeMapper @Inject constructor(
) : Mapper<Employee, Subscriber> {
    override fun map(from: Employee): Subscriber = from.run {
        Subscriber(
            email = email,
            name = fullName,
            imageUrl = profilePictureUrl,
            homeLocation = homeLocation,
            workLocation = workLocation,
            offDays = offDays,
            phone = phone
        )
    }
}
