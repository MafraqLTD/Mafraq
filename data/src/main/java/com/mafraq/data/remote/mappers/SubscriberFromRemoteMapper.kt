package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.MapperList
import com.mafraq.data.entities.Subscriber
import com.mafraq.data.entities.profile.DayOff
import com.mafraq.data.remote.dataSource.subscription.employee.SubscribeRequestStatus
import com.mafraq.data.remote.models.SubscriberRemote
import timber.log.Timber
import javax.inject.Inject


class SubscriberFromRemoteMapper @Inject constructor(

) : MapperList<SubscriberRemote, Subscriber>{
    override fun map(from: SubscriberRemote): Subscriber = from.run {
        Subscriber(
            email = email,
            name = name,
            imageUrl = imageUrl,
            homeLocation = homeLocation,
            workLocation = workLocation,
            offDays = offDays.map { DayOff.fromString(it) },
            phone = phone,
            active = active,
            status = SubscribeRequestStatus.fromString(status)
        )
    }

    override fun mapList(from: List<SubscriberRemote>): List<Subscriber> =
        from.map(::map)
}
