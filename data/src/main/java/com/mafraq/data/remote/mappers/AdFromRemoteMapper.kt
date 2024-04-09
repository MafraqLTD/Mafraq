package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.MapperList
import com.mafraq.data.entities.home.Ad
import com.mafraq.data.remote.models.AdRemote
import javax.inject.Inject


class AdFromRemoteMapper @Inject constructor(
) : MapperList<AdRemote, Ad> {

    override fun map(from: AdRemote): Ad = from.run {
        Ad(
            id = id.orEmpty(),
            title = title.orEmpty(),
            description = description.orEmpty(),
            imageUrl = imageUrl.orEmpty()
        )
    }

    override fun mapList(from: List<AdRemote>): List<Ad> = from.map(::map)
}
