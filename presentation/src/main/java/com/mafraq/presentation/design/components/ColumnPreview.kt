package com.mafraq.presentation.design.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mafraq.presentation.design.theme.MafraqTheme


@Composable
fun ColumnPreview(
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(MafraqTheme.sizes.small),
    content: @Composable ColumnScope.() -> Unit,
) = MafraqTheme {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(sizes.small),
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement,
        content = content
    )
}
