package com.mafraq.employee.features.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mafraq.employee.features.home.HomeUiState
import com.mafraq.employee.features.map.components.Rating
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.components.home.CarCard
import com.mafraq.presentation.design.components.home.TwoLineText
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.string

@Composable
fun PendingSubscribeRequest(state: HomeUiState, onCancelClick: () -> Unit) {
    CarCard(
        text = R.string.cancel.string,
        onClick = onCancelClick,
        containerColor = MafraqTheme.colors.secondary,
        rowContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MafraqTheme.sizes.medium)
            ) {
                Row {
                    AsyncImage(
                        model = state.pendingDriver?.profilePictureUrl.orEmpty(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(MafraqTheme.shapes.medium),
                        contentScale = ContentScale.Crop
                    )

                    Spacer.Small(vertical = false)

                    TwoLineText(
                        title = state.pendingDriver?.fullName.orEmpty(),
                        description = state.pendingDriver?.carName.orEmpty(),
                        titleColor = MafraqTheme.colors.background,
                        descriptionColor = MafraqTheme.colors.background
                    )

                    TwoLineText(
                        title = R.string.pending.string,
                        description = state.pendingDriver?.carNumber.orEmpty(),
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxWidth(),
                        titleColor = MafraqTheme.colors.warning,
                        descriptionColor = MafraqTheme.colors.background
                    )
                }

                Spacer.Small()

                Rating(
                    rate = state.pendingDriver?.rating.orEmpty(),
                    color = MafraqTheme.colors.background
                )

                Spacer.Small()

                Text(
                    text = R.string.phone_with_arg.string(state.pendingDriver?.phone.orEmpty()),
                    color = MafraqTheme.colors.background,
                    style = MafraqTheme.typography.label
                )
            }
        },
    ) {

    }
}