package com.mafraq.presentation.features.authentication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string

@Composable
fun EmailPasswordError(
    isVisible: Boolean = false,
) {

    if (isVisible) {

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
            modifier = Modifier.fillMaxWidth(),
            shape = MafraqTheme.shapes.medium,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.background(MafraqTheme.colors.warningContainer)
            ) {

                Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(12.dp)) {

                    Icon(
                        painter = R.drawable.ic_wrong.painter,
                        contentDescription = null,
                        tint = Color.Unspecified,
                    )

                }

                Spacer.Small(vertical = false)

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = R.string.EmailPasswordError.string,
                        style = MafraqTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun EmailPasswordErrorPreview() = EmailPasswordError(
    true
)
