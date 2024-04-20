package com.mafraq.presentation.features.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mafraq.data.entities.profile.DayOff
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.optionalComposable
import com.mafraq.presentation.utils.extensions.painter

@Composable
fun OffDaysChipset(
    modifier: Modifier = Modifier,
    items: List<DayOff> = DayOff.entries,
    selectedItems: Set<DayOff>,
    onItemChanged: (selected: Boolean, item: DayOff) -> Unit,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            space = MafraqTheme.sizes.medium,
            alignment = Alignment.CenterHorizontally
        ),
    ) {
        items.forEach { item ->
            val isSelected = item in selectedItems
            FilterChip(
                label = {
                    Text(
                        text = item.name,
                        style = MafraqTheme.typography.body
                    )
                },
                leadingIcon = optionalComposable(isSelected) {
                    Icon(
                        painter = R.drawable.ic_unread.painter,
                        contentDescription = null
                    )
                },
                shape = MafraqTheme.shapes.medium,
                selected = isSelected,
                onClick = {
                    onItemChanged(!isSelected, item)
                }
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun Preview() = ColumnPreview {
    OffDaysChipset(
        selectedItems = setOf(DayOff.Sunday, DayOff.Tuesday, DayOff.Saturday),
        onItemChanged = { _, _ -> })
}
