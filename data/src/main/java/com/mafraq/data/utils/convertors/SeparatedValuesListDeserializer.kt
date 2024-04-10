package com.mafraq.data.utils.convertors

import com.google.gson.*
import com.mafraq.data.entities.SeparatedValuesList
import java.lang.reflect.Type


class SeparatedValuesListDeserializer : JsonDeserializer<SeparatedValuesList<String>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): SeparatedValuesList<String> {
        val stringList = json?.asString
            ?.split(";")
            ?.filter(String::isNotBlank)
            ?: emptyList()
        return SeparatedValuesList(stringList)
    }
}
