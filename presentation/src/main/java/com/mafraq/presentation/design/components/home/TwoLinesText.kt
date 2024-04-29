package com.mafraq.presentation.design.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.theme.MafraqTheme


@Composable
fun TwoLineText(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    titleColor: Color = MafraqTheme.colors.primary,
    descriptionColor: Color = titleColor,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    Column(
        horizontalAlignment = horizontalAlignment,
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MafraqTheme.typography.titleMedium,
            color = titleColor
        )
        Spacer.Small()
        Text(
            text = description,
            style = MafraqTheme.typography.label,
            color = descriptionColor
        )
    }
}
