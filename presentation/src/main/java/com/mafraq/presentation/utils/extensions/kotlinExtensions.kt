package com.mafraq.presentation.utils.extensions

import androidx.annotation.ColorInt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.graphics.Color
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter


fun @receiver:ColorInt Int?.toColor() = this?.run { Color(this) }


val Map<String, List<String>>.keysList
    get() = keys.toList()

fun <T> T.optionalComposable(
    shouldExecute: Boolean = true,
    content: @Composable (T) -> Unit,
): @Composable (() -> Unit)? =
    takeIf { shouldExecute && this != null }
        ?.let { { content(this) } }

fun <T> T.optional(
    shouldExecute: Boolean = true,
    block: (T) -> Unit,
): (() -> Unit)? =
    takeIf { shouldExecute && this != null }
        ?.let { { block(this) } }

fun optional(
    shouldExecute: Boolean = true,
    block: () -> Unit,
): (() -> Unit)? = shouldExecute.takeIf { it }?.let { { block() } }

fun optionalComposable(
    shouldExecute: Boolean = true,
    content: @Composable () -> Unit,
): @Composable (() -> Unit)? = shouldExecute.takeIf { it }?.let { { content() } }

private const val EMPTY_STRING = ""
fun emptyString() = EMPTY_STRING

fun String.firstOrEmpty(): String = (this.firstOrNull() ?: EMPTY_STRING).toString()

fun MutableIntState.isGreaterThanZero() = intValue > 0

val MutableIntState.stringValue
    get() = intValue.toString()

fun Int.isEmpty() = this <= 0
fun Int.isNotEmpty() = this > 0

fun String.toTitleCase(): String {
    val titleCaseWords = split(" ")
        .map { it.replaceFirstChar(Char::titlecase) }
    return titleCaseWords.joinToString(" ")
}

fun <T> List<T>.middle(before: Int = 1) = this[(size / 2) - before]

operator fun<T> Pair<T, T>.get(index: Int) = if (index == 0) first else second

fun Long.toLocalDate(): LocalDate = Instant.ofEpochMilli(this)
    .atZone(ZoneId.systemDefault())
    .toLocalDate()


fun<T> List<T>.middle() = this[size / 2]
