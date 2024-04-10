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
    ): LocationRemote  = json?.asString.fromJson<LocationRemote>()
}
