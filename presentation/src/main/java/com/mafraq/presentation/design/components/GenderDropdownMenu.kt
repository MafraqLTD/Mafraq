package com.mafraq.presentation.design.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.mafraq.data.entities.profile.Gender
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.container.OutlinedContainer
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.optional
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string


@Composable
fun GenderDropdownMenu(
    value: Gender?,
    onSelected: (Gender) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    OutlinedContainer(
        label = R.string.gender.string,
        onClick = optional(value == null) { isExpanded = !isExpanded }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = MafraqTheme.sizes.medium)
        ) {
            Icon(
                painter = R.drawable.ic_gender.painter,
                contentDescription = null,
                tint = MafraqTheme.colors.primary,
                modifier = Modifier.scale(1.35f)
            )

            Spacer.Medium(vertical = false)
            if (value != null)
                Text(
                    text = value.nameResId.string,
                    style = MafraqTheme.typography.body
                )
            else
//            ExposedDropdownMenuBox(
//                expanded = isExpanded,
//                onExpandedChange = { newValue ->
//                    isExpanded = newValue
//                }
//            ) {
            // FIXME(
            //  Use ExposedDropdownMenuBox when this bug addressed
            //  https://issuetracker.google.com/issues/323694447
            //  )
                DropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = {
                        isExpanded = false
                    },
                    offset = DpOffset(0.dp, MafraqTheme.sizes.extraSmall),
                    properties = PopupProperties(focusable = false, clippingEnabled = true),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .clip(MafraqTheme.shapes.medium)
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(text = R.string.male.string)
                        },
                        onClick = {
                            onSelected(Gender.Male)
                            isExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = R.string.female.string)
                        },
                        onClick = {
                            onSelected(Gender.Female)
                            isExpanded = false
                        }
                    )
                }
        }
    }
}
