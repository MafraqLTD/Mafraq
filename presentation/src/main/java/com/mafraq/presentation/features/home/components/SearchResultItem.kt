package com.mafraq.presentation.features.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.painter


@Composable
fun SearchResultItem(
    headerText: String,
    bodyText: String,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MafraqTheme.shapes.large
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Box(contentAlignment = Alignment.Center) {

                Surface(
                    shape = RoundedCornerShape(
                        topEnd = MafraqTheme.sizes.large,
                        bottomEnd = MafraqTheme.sizes.large,
                    ),
                    color = MafraqTheme.colors.background.copy(alpha = 0.7f),
                    modifier = Modifier.size(48.dp)
                ) {}

                Icon(
                    painter = R.drawable.ic_location_filled.painter,
                    contentDescription = null,
                    tint = Color.Unspecified,
                )

            }

            Spacer.Small(vertical = false)

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


@Composable
@Preview(showBackground = true)
private fun Preview() = ColumnPreview(
    contentPadding = PaddingValues(MafraqTheme.sizes.medium)
){
    SearchResultItem("University of Baghdad", "Iraq,street 2018/9")
    SearchResultItem("University of Baghdad", "Iraq,street 2018/9")
    SearchResultItem("University of Baghdad", "Iraq,street 2018/9")
}
