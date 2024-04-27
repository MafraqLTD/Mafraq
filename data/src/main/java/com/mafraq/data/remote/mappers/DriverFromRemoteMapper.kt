package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.MapperList
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.map.DriverSubscriptionStatus
import com.mafraq.data.remote.models.ApiResponseRemote
import com.mafraq.data.remote.models.DriverRemote
import javax.inject.Inject


class DriverFromRemoteMapper @Inject constructor()
    : MapperList<ApiResponseRemote.RowRemote<DriverRemote>, Driver> {

    override fun map(from: ApiResponseRemote.RowRemote<DriverRemote>) = from.item.run {
        Driver(
            rowId = from.rowId,
            birthday = birthday.orEmpty(),
            email = email.orEmpty(),
            fullName = fullName.orEmpty(),
            id = id.orEmpty(),
            location = location.toDomain(),
            phone = phone.orEmpty(),
            profilePicture = profilePicture.orEmpty(),
            car = car.orEmpty(),
            carNumber = carNumber.orEmpty(),
            rating = rating?.toDoubleOrNull()?.toString().orEmpty(),
            snippet = snippet.orEmpty(),
            subscribers = toSubscribers(),
            subscriptionStatus = DriverSubscriptionStatus.fromString(subscriptionStatus)
        )
    }

    override fun mapList(from: List<ApiResponseRemote.RowRemote<DriverRemote>>): List<Driver> = from.map(::map)
}
