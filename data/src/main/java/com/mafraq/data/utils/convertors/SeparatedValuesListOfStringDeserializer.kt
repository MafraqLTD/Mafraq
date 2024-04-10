package com.mafraq.data.utils.convertors

import com.google.gson.*
import com.mafraq.data.entities.SeparatedValuesList
import com.mafraq.data.utils.toStringList
import java.lang.reflect.Type


class SeparatedValuesListOfStringDeserializer : JsonDeserializer<SeparatedValuesList<String>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): SeparatedValuesList<String> = SeparatedValuesList(json.toStringList())

}
