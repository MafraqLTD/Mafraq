package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.mafraq.data.entities.Subscriber
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.local.profile.ProfileLocalDataSource
import javax.inject.Inject


class SubscriberFromEmployeeMapper @Inject constructor(
) : Mapper<Employee, Subscriber> {
    override fun map(from: Employee): Subscriber = from.run {
        Subscriber(
            id = id,
            name = fullName,
            imageUrl = profilePicture,
            homeLocation = homeLocation,
            workLocation = workLocation
        )
    }
}
