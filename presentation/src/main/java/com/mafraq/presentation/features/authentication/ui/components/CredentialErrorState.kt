package com.mafraq.presentation.features.authentication.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.mafraq.presentation.utils.extensions.string


@Composable
fun CredentialErrorState(
    errorMessage: String? = null,
    isInvalid: () -> Boolean = { false },
) {
    AnimatedVisibility(isInvalid()) {
        Card(
            modifier = Modifier
                .height(MafraqTheme.sizes.large)
                .fillMaxWidth(),
            shape = MafraqTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            colors = CardDefaults.cardColors(containerColor = MafraqTheme.colors.warningContainer)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer.Small(vertical = false)

                Icon(
                    painter = R.drawable.ic_wrong.painter,
                    contentDescription = null,
                    tint = Color.Unspecified,
                )

                Spacer.Small(vertical = false)

                Text(
                    text = errorMessage ?: R.string.wrong_email_or_password.string,
                    style = MafraqTheme.typography.label
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun Preview() = ColumnPreview(
    contentPadding = PaddingValues(MafraqTheme.sizes.medium)
) {
    CredentialErrorState { false }
}
