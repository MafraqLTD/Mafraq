package com.mafraq.presentation.design.components.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.detectTapGestures


@Composable
fun AppContainer(
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
        ContainerCard(content = { content(focusManager) })
    }
}


@Composable
private fun ContainerCard(
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
            ),
        content = content
    )
}


@Preview
@Composable
fun AppContainerPreview() = AppContainer {
        Text(text = "One")
        Text(text = "Two")
        Text(text = "Three")
}