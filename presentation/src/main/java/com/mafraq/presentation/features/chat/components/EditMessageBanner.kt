package com.mafraq.presentation.features.chat.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string


@Composable
fun EditMessageBanner(
    message: String,
    isEditMode: Boolean,
    onClearClick: () -> Unit
) {
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl

    @Composable
    fun EditIcon() {
        Icon(
            painter = R.drawable.ic_edit.painter,
            contentDescription = null,
            tint = MafraqTheme.colors.primary,
            modifier = Modifier.size(20.dp)
        )
    }

    @Composable
    fun ClearIcon() {
        IconButton(
            onClick = onClearClick,
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
    }

    AnimatedVisibility(visible = isEditMode) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MafraqTheme.sizes.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isRtl)
                ClearIcon()

            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isRtl.not())
                    EditIcon()

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = R.string.edit_message.string,
                        color = MafraqTheme.colors.primary,
                        style = MafraqTheme.typography.titleSmall,
                    )

                    Text(
                        text = message,
                        color = MafraqTheme.colors.contentPrimary.copy(alpha = 0.75f),
                        style = MafraqTheme.typography.label,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer.Medium(vertical = false)

                if (isRtl)
                    EditIcon()
            }

            if (isRtl.not())
                ClearIcon()
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() = ColumnPreview {
    EditMessageBanner(message = "Hello World", isEditMode = true) {

    }
}