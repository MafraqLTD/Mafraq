package com.mafraq.data.utils

import android.content.Context
import android.content.SharedPreferences
import android.location.LocationManager
import com.altaie.prettycode.core.base.Resource
import com.google.android.gms.tasks.Task
import com.mafraq.data.entities.map.Location
import com.mafraq.data.remote.errors.EmptyBodyException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import android.location.Location as AndroidLocation


fun SharedPreferences.putString(key: String, value: String) = this.edit()
    .putString(key, value)
    .apply()

fun SharedPreferences.delete(key: String) = this.edit()
    .remove(key)
    .apply()

fun String?.toJsonObject(): JsonObject? = runCatching {
    Json.parseToJsonElement(this!!).jsonObject
}.getOrNull()

fun String?.getValueOf(key: String) = runCatching {
    toJsonObject()?.get(key)?.jsonPrimitive?.content
}.getOrNull()

fun <T> Resource<T>?.getOrThrowEmpty() = this?.toData ?: throw EmptyBodyException()

val Context.locationManager: LocationManager
    get() = getSystemService(Context.LOCATION_SERVICE) as LocationManager

fun AndroidLocation.toDomain() = Location(
    latitude = latitude,
    longitude = longitude
)

fun String.titlecase() = lowercase().replaceFirstChar { it.uppercase() }

suspend fun <T> Task<T>.awaitBoolean(logLabel: String): Boolean =
    suspendCoroutine { continuation ->
        addOnCompleteListener {
            if (isSuccessful)
                Timber.d("$logLabel -> Successful")
            else
                Timber.e("$logLabel -> Failed")

            continuation.resume(isSuccessful)
        }
    }
