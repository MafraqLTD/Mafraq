package com.mafraq.presentation.features.authentication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.container.AppContainer
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string


@Composable
fun AuthContainer(
    title: String,
    description: String,
    content: @Composable ColumnScope.(FocusManager) -> Unit
) {
    AppContainer { focusManager ->
        CardHeader(
            textHeader = title,
            textSubHeader = description,
            logo = R.drawable.logo_wide.painter,
        )

        Spacer.Large()

        content(focusManager)
    }
}


@Composable
private fun CardHeader(
    textHeader: String,
    textSubHeader: String,
    logo: Painter,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MafraqTheme.sizes.large)
    ) {
        Image(
            painter = logo,
            contentDescription = null,
        )
        Column(verticalArrangement = Arrangement.spacedBy(MafraqTheme.sizes.small)) {
            Text(
                text = textHeader,
                style = MafraqTheme.typography.titleLarge,
                color = MafraqTheme.colors.contentPrimary
            )
            Text(
                text = textSubHeader,
                style = MafraqTheme.typography.body,
                color = MafraqTheme.colors.contentTertiary
            )
        }
    }
}


@Preview
@Composable
fun AuthContainerPreview() = ColumnPreview {
    AuthContainer(
        title = R.string.welcome.string,
        description = R.string.login_description.string
    ) {}
}
