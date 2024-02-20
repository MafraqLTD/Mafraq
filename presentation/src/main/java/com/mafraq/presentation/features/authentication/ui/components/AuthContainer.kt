package com.mafraq.presentation.features.authentication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.detectTapGestures
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string


@Composable
fun AuthContainer(
    title: String,
    description: String,
    content: @Composable ColumnScope.(FocusManager) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .detectTapGestures(onTap = { focusManager.clearFocus() })
            .padding(MafraqTheme.sizes.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ContainerCard(
            textHeader = title,
            textSubHeader = description,
            logo = R.drawable.logo_wide.painter,
            content = { content(focusManager) }
        )
    }
}


@Composable
private fun ContainerCard(
    textHeader: String,
    textSubHeader: String,
    logo: Painter,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(MafraqTheme.sizes.small)
            .clip(shape = MafraqTheme.shapes.medium)
            .background(MafraqTheme.colors.surface)
            .padding(
                horizontal = MafraqTheme.sizes.medium,
                vertical = MafraqTheme.sizes.large
            )
    ) {
        CardHeader(
            textHeader = textHeader,
            textSubHeader = textSubHeader,
            logo = logo,
        )

        Spacer.Large()

        content()
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
        Icon(

            painter = logo,
            contentDescription = null,
            tint = MafraqTheme.colors.primary
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
