package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.MapperList
import com.mafraq.data.entities.home.Ad
import com.mafraq.data.remote.models.AdRemote
import com.mafraq.data.remote.models.ApiResponseRemote
import javax.inject.Inject


class AdFromRemoteMapper @Inject constructor(
) : MapperList<ApiResponseRemote.RowRemote<AdRemote>, Ad> {

    override fun map(from: ApiResponseRemote.RowRemote<AdRemote>): Ad = from.item.run {
        Ad(
            rowId = from.rowId,
            id = id.orEmpty(),
            title = title.orEmpty(),
            description = description.orEmpty(),
            imageUrl = imageUrl.orEmpty(),
            isDriverAd = isDriverAd.toBoolean()
        )
    }

    override fun mapList(from: List<ApiResponseRemote.RowRemote<AdRemote>>): List<Ad> = from.map(::map)
}
