package com.mafraq.employee.features.map.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.BottomSheet
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.components.buttons.TextButton
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.design.theme.MafraqTheme.shapes
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.design.theme.MafraqTheme.typography
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string


@Composable
fun DriverBottomSheet(
    name: String,
    snippet: String,
    profilePic: String,
    rating: String,
    car: String,
    carNumber: String,
    onAccept: () -> Unit,
    onDismissRequest: () -> Unit
) {

    BottomSheet(
        onDismissRequest = onDismissRequest
    ) { hideSheet ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(sizes.small),
            modifier = Modifier
                .padding(start = sizes.small)
                .padding(horizontal = sizes.small),
        ) {
            Text(
                text = R.string.send_subscribe_request.string,
                modifier = Modifier.weight(1f)
            )

            TextButton(
                text = R.string.cancel.string,
                onClick = hideSheet
            )

            TextButton(
                text = R.string.accept.string,
                onClick = onAccept
            )
        }

        HorizontalDivider()

        Spacer.Medium()

        Row(
            modifier = Modifier.padding(horizontal = sizes.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = profilePic,
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(shapes.medium),
                clipToBounds = true,
                contentScale = ContentScale.Crop
            )

            Spacer.Small(vertical = false)

            DriverDetails(
                name = name,
                rating = rating,
                car = car,
                carNumber = carNumber
            )

        }

        Spacer.Medium()

        Text(
            text = snippet,
            modifier = Modifier.padding(horizontal = sizes.medium),
            style = typography.body
        )

        Spacer.Large()
    }
}

@Composable
private fun RowScope.DriverDetails(
    name: String,
    rating: String,
    car: String,
    carNumber: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.weight(1f)
    ) {
        Text(
            text = name,
            style = typography.titleMedium
        )

        Rating(rate = rating)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = car,
            style = typography.titleMedium
        )

        Spacer.ExtraSmall()

        Text(
            text = carNumber,
            style = typography.label
        )
    }
}


@Composable
fun Rating(rate: String, color: Color = MafraqTheme.colors.primary) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = rate,
            textAlign = TextAlign.Center,
            color = color,
            style = MaterialTheme.typography.labelLarge,
        )

        Spacer.ExtraSmall(vertical = false)

        repeat(rate.toDouble().toInt()) {
            Icon(
                painter = R.drawable.ic_star.painter,
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(sizes.medium)
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun Preview() = ColumnPreview {
    DriverBottomSheet(
        name = "Ahmed Mones",
        profilePic = "",
        rating = "4.5",
        car = "Toyota Rav4 2021",
        carNumber = "1234",
        snippet = "Ahmed Mones",
        onAccept = {},
        onDismissRequest = {}
    )
}
