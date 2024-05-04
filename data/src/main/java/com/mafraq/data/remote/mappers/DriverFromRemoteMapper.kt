package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.MapperList
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.map.DriverSubscriptionStatus
import com.mafraq.data.remote.models.ApiResponseRemote
import com.mafraq.data.remote.models.DriverRemote
import com.mafraq.data.repository.map.MapPlacesRepositoryImpl
import com.mafraq.data.utils.toFormattedString
import com.mafraq.data.utils.toLocalDate
import javax.inject.Inject


class DriverFromRemoteMapper @Inject constructor(
    private val placesRepository: MapPlacesRepositoryImpl
) : MapperList<ApiResponseRemote.RowRemote<DriverRemote>, Driver> {

    override fun map(from: ApiResponseRemote.RowRemote<DriverRemote>) = from.item.run {
        Driver(
            rowId = from.rowId,
            birthday = birthday?.toLocalDate()?.toFormattedString("yyyy-MM-dd").orEmpty(),
            email = email.orEmpty(),
            fullName = fullName.orEmpty(),
            id = id.orEmpty(),
            location = location.toDomain(),
            phone = phone.orEmpty(),
            profilePictureUrl = profilePictureUrl.orEmpty(),
            carName = car.orEmpty(),
            carNumber = carNumber.orEmpty(),
            gender = gender.orEmpty(),
            nationalId = nationalId.orEmpty(),
            rating = rating?.toDoubleOrNull()?.toString().orEmpty(),
            snippet = snippet.orEmpty(),
            subscribers = toSubscribers(),
            subscriptionStatus = DriverSubscriptionStatus.fromString(subscriptionStatus)
        )
    }

    override fun mapList(from: List<ApiResponseRemote.RowRemote<DriverRemote>>): List<Driver> =
        from.map(::map)

    suspend fun find(
        from: List<ApiResponseRemote.RowRemote<DriverRemote>>,
        selector: (Driver) -> Boolean
    ): Driver {
        val driver = mapList(from).firstOrNull(selector) ?: error("Driver not found")
        var location = driver.location
        with(location) {
            if (formattedAddress.isEmpty())
                location = placesRepository.getLocationInfo(
                    latitude = latitude,
                    longitude = longitude
                )
        }

        return driver.copy(location = location)
    }
}
