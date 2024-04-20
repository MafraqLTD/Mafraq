package com.mafraq.presentation.design.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.mafraq.presentation.R
import com.mafraq.presentation.utils.extensions.string
import com.mafraq.presentation.utils.extensions.toLocalDate
import java.time.LocalDate


@Composable
fun DatePickerDialog(
    visible: Boolean,
    initialDate: LocalDate? = null,
    onSelect: (LocalDate) -> Unit,
    onDismissRequest: (Boolean) -> Unit
) {
    if (visible) {
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initialDate?.toEpochDay())
        val confirmEnabled by remember { derivedStateOf { datePickerState.selectedDateMillis != null } }

        androidx.compose.material3.DatePickerDialog(
            onDismissRequest = { onDismissRequest(false) },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDismissRequest(false)
                        datePickerState.selectedDateMillis?.toLocalDate()?.let { onSelect(it) }
                    },
                    enabled = confirmEnabled
                ) {
                    Text(R.string.confirm.string)
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismissRequest(false) }) {
                    Text(R.string.cancel.string)
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
