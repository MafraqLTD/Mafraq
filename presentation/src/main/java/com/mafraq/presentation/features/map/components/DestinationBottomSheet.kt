package com.mafraq.presentation.features.map.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.AppButton
import com.mafraq.presentation.design.components.AppOutlinedTextField
import com.mafraq.presentation.design.components.BottomSheet
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.theme.MafraqTheme.colors
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.design.theme.MafraqTheme.typography
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string


@Composable
fun DestinationBottomSheet(
    destination: String,
    isVisible: Boolean,
    onFindDestinationClick: () -> Unit,
    onMyLocationClick: () -> Unit,
    onNavigateBack: () -> Unit,
    onSelectDestination: () -> Unit,
    onDismissRequest: () -> Unit,
) {

    BottomSheet(
        isVisible = isVisible,
        onDismissRequest = onDismissRequest
    ) { hideSheet ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            IconButton(onClick = onNavigateBack,) {
                Icon(
                    painter = R.drawable.ic_back_arrow.painter,
                    contentDescription = null,
                    tint = colors.primary,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = R.string.where_is_your_destination.string,
                style = typography.titleLarge,
                modifier = Modifier.offset(x = -sizes.small)
            )

            IconButton(
                onClick = onMyLocationClick,
                modifier = Modifier.padding(end = sizes.medium),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = colors.primary
                )
            ) {
                Icon(
                    painter = R.drawable.ic_gps.painter,
                    contentDescription = null,
                    tint = colors.onPrimary,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Spacer.Large()

        AppOutlinedTextField(
            value = destination,
            onValueChange = {},
            readOnly = true,
            leadingIcon = R.drawable.ic_location.painter,
            trailingIcon = R.drawable.ic_search.painter,
            onTrailingIconClick = onFindDestinationClick,
            modifier = Modifier
                .padding(horizontal = sizes.medium)
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colors.contentBorder,
                unfocusedBorderColor = colors.contentBorder,
            )
        )

        Spacer.CustomSpace(distance = 42.dp)

        AppButton(
            text = R.string.set_destination.string,
            onClick = {
                hideSheet()
                onSelectDestination()
            },
            modifier = Modifier
                .height(56.dp)
                .padding(horizontal = sizes.medium)
                .fillMaxWidth()
        )

        Spacer.CustomSpace(distance = 42.dp)
    }
}


@Composable
@Preview(showBackground = true)
private fun Preview() = ColumnPreview {
    DestinationBottomSheet(
        destination = "Street,124,ninavah collage",
        isVisible = true,
        onDismissRequest = {},
        onSelectDestination = {},
        onFindDestinationClick = {},
        onMyLocationClick = {},
        onNavigateBack = {},
    )
}
