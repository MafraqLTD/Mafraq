package com.mafraq.data.utils

import android.content.Context
import android.content.SharedPreferences
import android.location.LocationManager
import com.altaie.prettycode.core.base.Resource
import com.altaie.prettycode.core.utils.extenstions.fromJson
import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.gson.JsonElement
import com.mafraq.data.entities.map.Location
import com.mafraq.data.remote.errors.EmptyBodyException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone
import java.util.UUID
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

fun String.titlecase() = lowercase().replaceFirstChar(Char::uppercase)

fun String.pascalcase() = lowercase().split(" ")
    .joinToString(transform = String::titlecase, separator = " ")

/**
 * Awaits the completion of the task and returns a boolean value indicating whether the task was successful.
 *
 * @param logLabel A label to use for logging.
 * @return True if the task was successful, false otherwise.
 */
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

/**
 * Converts a JSON element to a list of strings.
 *
 * @receiver The JSON element to convert.
 * @return A list of strings, or an empty list if the JSON element is null or empty.
 */
fun JsonElement?.toStringList(separator: String = ";"): List<String> = this
    ?.asString
    ?.split(separator)
    ?.filter(String::isNotBlank)
    ?: emptyList()


/**
 * Maps a list of strings to a list of objects of type `R`.
 *
 * @return A list of objects of type `R`, or an empty list if the input list is null.
 */
inline fun <reified R> List<String>?.mapToListOf(): List<R> = this?.map {
    it.fromJson<R>()
} ?: emptyList()


/**
 * Converts a [Timestamp] object to a formatted date and time string.
 *
 * @return The formatted date and time string.
 */
fun Timestamp.toFormattedDateTime(): String {
    val outputFormat = SimpleDateFormat("hh:mm a", Locale.US).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return outputFormat.format(toDate())
}

fun String.Companion.generateRandomId(): String =
    UUID.randomUUID().toString().split('-').last()

fun<T> List<T>.serializedWithSeparator(transform: (T) -> String): String =
    joinToString(";", transform = transform)

fun String.toStringUrl(path: String = "") = StringBuilder(this).apply {
    if (!endsWith('/')) append('/')

    if (path.isNotEmpty()) {
        append(path.trimStart('/'))

        if (!endsWith('/')) append('/')
    }
}.toString()

fun LocalDate.toFormattedString(format: String = "dd/MM/yyyy"): String {
    return format(DateTimeFormatter.ofPattern(format))
}
