package com.mafraq.data.utils.convertors

import com.altaie.prettycode.core.utils.extenstions.fromJson
import com.google.gson.*
import com.mafraq.data.remote.models.LocationRemote
import java.lang.reflect.Type


class LocationDeserializer : JsonDeserializer<LocationRemote> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocationRemote {
        val latLang = json?.asString?.split(",")
        return if (latLang?.size == 2)
            LocationRemote(
                latitude = latLang.first().toDouble(),
                longitude = latLang.last().toDouble()
            )
        else
            json?.asString.fromJson<LocationRemote>()
    }
}
