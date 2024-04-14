package com.mafraq.presentation.features.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.AppCard
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.string


@Composable
fun InfoCard(
    profilePic: String,
    name: String,
    collageName: String,
    title: String,
    area: String,
    phone: String,
    distance: String,
    workDays: String,
    onLocationClick: () -> Unit = {},
    onUnsubscribeClick: () -> Unit = {},
) {
    AppCard(
        containerColor = MafraqTheme.colors.onPrimary,
        contentPadding = PaddingValues(MafraqTheme.sizes.medium),
        rowContent = {
            AsyncImage(
                model = profilePic,
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(MafraqTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            Spacer.Small(vertical = false)

            TwoLineText(
                title = name,
                description = collageName,
                titleColor = MafraqTheme.colors.contentPrimary,
                descriptionColor = MafraqTheme.colors.secondary
            )

            TwoLineText(
                title = title,
                description = area,
                horizontalAlignment = Alignment.End,
                modifier = Modifier.fillMaxWidth()
            )
        },
        content = {
            Spacer.Small()

            repeat(times = 3){ index ->
                Text(
                    text = listOf(
                        R.string.phone_with_arg.string(phone),
                        R.string.workdays_with_arg.string(workDays),
                        R.string.distance_with_arg.string(distance),
                    )[index],
                    style = MafraqTheme.typography.titleMedium,
                    color = MafraqTheme.colors.secondary
                )
                Spacer.ExtraSmall()
            }

            Spacer.Medium()

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(
                    onClick = onUnsubscribeClick,
                    shape = MafraqTheme.shapes.medium,
                    content = { Text(text = R.string.unsubscribe.string) },
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = onLocationClick,
                    contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                ) {
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer.Small(vertical = false)
                    Text(text = R.string.location.string)
                }
            }
        }

    )
}

@Composable
fun TwoLineText(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    titleColor: Color = MafraqTheme.colors.primary,
    descriptionColor: Color = titleColor,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    Column(
        horizontalAlignment = horizontalAlignment,
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MafraqTheme.typography.titleMedium,
            color = titleColor
        )
        Spacer.Small()
        Text(
            text = description,
            style = MafraqTheme.typography.label,
            color = descriptionColor
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun Preview() = ColumnPreview {
    InfoCard(
        profilePic = "",
        name = "Mostafa mohamed",
        collageName = "collageName",
        phone = "05532415",
        title = "Subscribed",
        area = "Zhoor",
        distance = "1.4 km",
        workDays = "Mon, Sun, Tus",
    )
}
