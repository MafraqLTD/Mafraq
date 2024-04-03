package com.mafraq.data.utils

import android.content.Context
import android.location.LocationManager
import com.altaie.prettycode.core.exceptions.ResponseException
import com.altaie.prettycode.core.utils.extenstions.fromJson
import com.mafraq.data.entities.map.Location
import com.mafraq.data.models.ApiResponseDto
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import android.location.Location as AndroidLocation


fun String?.toJsonObject(): JsonObject? = runCatching {
    Json.parseToJsonElement(this!!).jsonObject
}.getOrNull()

fun String?.getValueOf(key: String) = runCatching {
    toJsonObject()?.get(key)?.jsonPrimitive?.content
}.getOrNull()

val ResponseException.errorMessage
    get() = runCatching {
        message.fromJson<ApiResponseDto>().message
    }.getOrNull()

val Context.locationManager: LocationManager
    get() = getSystemService(Context.LOCATION_SERVICE) as LocationManager

fun AndroidLocation.toDomain() = Location(
    latitude = latitude,
    longitude = longitude
)