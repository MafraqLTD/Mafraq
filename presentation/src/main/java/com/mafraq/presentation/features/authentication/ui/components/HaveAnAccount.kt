package com.mafraq.presentation.features.authentication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.clickableNoRipple
import com.mafraq.presentation.utils.extensions.string


@Composable
fun HaveAnAccount(
    text: String,
    clickableText: String,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = MafraqTheme.typography.label,
            color = MafraqTheme.colors.contentTertiary
        )

        Spacer.Small(vertical = false)

        Text(
            text = clickableText,
            style = MafraqTheme.typography.titleSmall,
            color = MafraqTheme.colors.primary,
            modifier = Modifier.clickableNoRipple(
                enabled = !isLoading,
                onClick = onClick
            )
        )
    }
}


@Preview
@Composable
fun HaveAnAccountPreview() = ColumnPreview {
    HaveAnAccount(
        text = R.string.dont_have_an_account.string,
        clickableText = R.string.register.string,
        isLoading = false,
        onClick = {},
    )
}
