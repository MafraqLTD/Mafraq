package com.mafraq.data.utils.convertors

import com.google.gson.*
import com.mafraq.data.entities.SeparatedValuesListOfString
import com.mafraq.data.utils.toStringList
import java.lang.reflect.Type


class SeparatedValuesListOfStringDeserializer : JsonDeserializer<SeparatedValuesListOfString> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): SeparatedValuesListOfString = SeparatedValuesListOfString(json.toStringList())

}
