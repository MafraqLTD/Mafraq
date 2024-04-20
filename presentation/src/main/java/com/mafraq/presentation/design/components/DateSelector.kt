package com.mafraq.presentation.design.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.mafraq.presentation.design.components.container.OutlinedContainer
import com.mafraq.presentation.design.theme.MafraqTheme
import java.time.LocalDate


@Composable
fun DateSelector(
    label: String,
    painter: Painter,
    initialDate: String,
    onDateSelected: (LocalDate) -> Unit
) {
    var visibility by remember { mutableStateOf(false) }

    OutlinedContainer(
        label = label,
        onClick = { visibility = !visibility }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = MafraqTheme.sizes.medium)
        ) {
            Icon(
                painter = painter,
                contentDescription = null,
                tint = MafraqTheme.colors.primary
            )

            Spacer.Medium(vertical = false)

            Text(
                text = initialDate,
                style = MafraqTheme.typography.body
            )
        }

        DatePickerDialog(
            visible = visibility,
            onSelect = onDateSelected,
            onDismissRequest = {
                visibility = false
            }
        )
    }
}
