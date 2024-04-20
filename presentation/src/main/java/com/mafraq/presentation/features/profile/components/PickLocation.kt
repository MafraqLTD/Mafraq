package com.mafraq.presentation.features.profile.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.components.container.OutlinedContainer
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.painter


@Composable
fun PickLocation(
    modifier: Modifier = Modifier,
    label: String? = null,
    formattedAddress: String,
    painter: Painter = R.drawable.ic_location.painter,
    onClick: () -> Unit = {}
) {

    OutlinedContainer(label = label, onClick = onClick, modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = MafraqTheme.sizes.medium)
        ) {
            Icon(
                painter = painter,
                contentDescription = null,
                tint = MafraqTheme.colors.primary
            )

            Spacer.Small(vertical = false)

            Text(
                text = formattedAddress,
                style = MafraqTheme.typography.body
            )
        }
    }

}


@Composable
@Preview(showBackground = true)
private fun Preview() {
    ColumnPreview(contentPadding = PaddingValues(MafraqTheme.sizes.medium)) {
        PickLocation(label = null, formattedAddress = "Iraq, Nineveh, Mosul")
        PickLocation(label = "Address", formattedAddress = "Iraq, Nineveh, Mosul")
    }
}
