package com.mafraq.presentation.features.notifications.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.altaie.prettycode.core.utils.VoidCallBack
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.theme.MafraqTheme.colors
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.design.theme.MafraqTheme.typography


@Composable
fun NotificationCard(
    modifier: Modifier = Modifier,
    title: String?,
    body: String?,
    date: String?,
    read: Boolean = true,
    enabled: Boolean = true,
    paddingValues: PaddingValues = PaddingValues(all = sizes.medium),
    containerColor: Color = colors.background,
    containerColorUnread: Color = colors.secondary,
    onClick: VoidCallBack = {}
) {
    val containerColorState by animateColorAsState(
        targetValue = if (read) containerColor else containerColorUnread,
        label = "containerColorState"
    )

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(sizes.default),
        colors = CardDefaults.cardColors(containerColor = containerColorState),
        enabled = enabled,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(paddingValues)
        ) {
            Text(
                text = title.orEmpty(),
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.W600)
            )
            Spacer.Medium(vertical = false)
            Text(
                text = date.orEmpty(),
                modifier = Modifier.weight(0.25f),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W500)
            )
        }

        if (body.isNullOrEmpty().not())
            Text(
                text = body.orEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = sizes.medium)
                    .padding(horizontal = sizes.medium),
                style = typography.label,
            )
    }
}


@Composable
@Preview(showBackground = true)
private fun Preview() = ColumnPreview {
    NotificationCard(
        title = "Driver has been changed!",
        body = "Name: Ahmed Mones\n" + "Car: Toyota Rav4 2021\n" + "Color: Magnetic Black",
        date = "11:20 AM",
        read = false
    )
}
