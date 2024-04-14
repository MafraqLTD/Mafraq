package com.mafraq.presentation.features.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.FilledIconButton
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
import com.mafraq.presentation.design.components.AppCard
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.theme.MafraqTheme


@Composable
fun ClientInfoCard(
    profilePic: String,
    name: String,
    collageName: String,
    title: String,
    area: String,
    phone: String,
    distance: String,
    workDays: String,
    onClick: () -> Unit
) {

    AppCard(
        modifier = Modifier.clickable(onClick = onClick),
        containerColor = MafraqTheme.colors.onPrimary,
        contentPadding = PaddingValues(MafraqTheme.sizes.medium),
        rowContent = {
            Column {

                Row {
                    AsyncImage(
                        model = profilePic,
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(MafraqTheme.shapes.medium),
                        clipToBounds = true,
                        contentScale = ContentScale.Crop
                    )

                    Spacer.Small(vertical = false)

                    Column {
                        Text(
                            text = name,
                            style = MafraqTheme.typography.titleMedium,
                            color = MafraqTheme.colors.contentPrimary
                        )
                        Spacer.Small()
                        Text(
                            text = collageName,
                            style = MafraqTheme.typography.label,
                            color = MafraqTheme.colors.secondary
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = title,
                            style = MafraqTheme.typography.titleMedium,
                            color = MafraqTheme.colors.primary
                        )
                        Spacer.Small()
                        Text(
                            text = area,
                            style = MafraqTheme.typography.label,
                            color = MafraqTheme.colors.primary
                        )
                    }

                }

                Spacer.Small()

                Text(
                    text = "Phone: $phone",
                    style = MafraqTheme.typography.titleMedium,
                    color = MafraqTheme.colors.secondary
                )
                Spacer.ExtraSmall()

                Text(
                    text = "WorkDays: $workDays",
                    style = MafraqTheme.typography.titleMedium,
                    color = MafraqTheme.colors.secondary
                )
                Spacer.ExtraSmall()
                Text(
                    text = "Distance: $distance",
                    style = MafraqTheme.typography.titleMedium,
                    color = MafraqTheme.colors.secondary
                )

                Spacer.Medium()

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedButton(
                        onClick = { TODO() },
                        shape = MafraqTheme.shapes.medium,
                        content = { Text(text = "Unsubscribe") },
                    )

                    Spacer(modifier = Modifier.weight(1f)) // Add a Spacer with weight

                    Button(
                        onClick = { TODO() },
                        contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                    ) {
                        Icon(
                            Icons.Filled.LocationOn,
                            contentDescription = "Localized description",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Location")
                    }
                }


            }
        }

    )
}


@Preview(showBackground = true)
@Composable
fun ClientInfoCardPreview() = ColumnPreview {
    ClientInfoCard(
        profilePic = "",
        name = "Mostafa mohamed",
        collageName = "collageName",
        phone = "05532415",
        title = "Subscribed",
        area = "Zhoor",
        distance = "1.4 km",
        workDays = "Mon, Sun, Tus",
        onClick = {}
    )
}
