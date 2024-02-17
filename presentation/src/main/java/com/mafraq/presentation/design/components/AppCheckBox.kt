package com.mafraq.presentation.design.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.clickableNoRipple


@Composable
fun AppCheckbox(
    modifier: Modifier = Modifier,
    label: String? = null,
    initialState: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit) = {},
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors(
        uncheckedColor = MafraqTheme.colors.contentBorder
    ),
) {
    var checked: Boolean by remember { mutableStateOf(initialState) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .absoluteOffset(x = (-14).dp)
            .clickableNoRipple(
                enabled = enabled,
                onClick = {
                    checked = !checked
                    onCheckedChange(checked)
                }
            )
    ) {
        Checkbox(
            checked = checked,
            colors = colors,
            enabled = enabled,
            onCheckedChange = {
                checked = !checked
                onCheckedChange(checked)
            }
        )

        label?.let {
            Text(
                text = it,
                color = MafraqTheme.colors.contentPrimary,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun AppCheckboxPreview() {
    AppCheckbox(label = "Checkbox")
}
