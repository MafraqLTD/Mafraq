package com.mafraq.presentation.features.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.painter

@Composable
fun InfoCard(
    headerText: String,
    bodyText: String,
) {
    Box(Modifier.fillMaxWidth()) {

        Card {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = R.drawable.ic_location_background.painter,
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.padding(end = 8.dp)
                )

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = headerText, style = MafraqTheme.typography.body)
                    Spacer.ExtraSmall()
                    Text(
                        text = bodyText,
                        style = MafraqTheme.typography.label,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun SimpleComposablePreview() = ColumnPreview{
    InfoCard("University of Baghdad", "Iraq,street 2018/9")
    InfoCard("University of Baghdad", "Iraq,street 2018/9")
    InfoCard("University of Baghdad", "Iraq,street 2018/9")
}
