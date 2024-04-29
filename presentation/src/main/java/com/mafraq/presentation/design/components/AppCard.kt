package com.mafraq.presentation.design.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import com.mafraq.presentation.design.theme.MafraqTheme.colors
import com.mafraq.presentation.design.theme.MafraqTheme.shapes


@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    rowModifier: ColumnScope.()->Modifier = { Modifier },
    containerColor: Color = colors.primary,
    contentColor: Color = colors.onPrimary,
    shape: Shape = shapes.medium,
    contentPadding: PaddingValues = PaddingValues(),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    rowContent: @Composable RowScope.() -> Unit = {},
    content: @Composable ColumnScope.() -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = shape
    ) {
        Column(modifier = Modifier.padding(contentPadding)) {
            Row(
                modifier = rowModifier(this).fillMaxWidth(),
                content = rowContent,
                horizontalArrangement = horizontalArrangement,
                verticalAlignment = verticalAlignment
            )
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppCardPreview() = ColumnPreview {
    AppCard(rowContent = {}) {}
}
