package com.mafraq.presentation.design.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.AppCard
import com.mafraq.presentation.design.components.TextIcon
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.painter
import com.mafraq.presentation.utils.extensions.string

@Composable
fun SupportCard(onClick: () -> Unit) {
    AppCard(
        modifier = Modifier.clickable(onClick = onClick),
        containerColor = MafraqTheme.colors.onPrimary,
        contentPadding = PaddingValues(MafraqTheme.sizes.medium),
        rowContent = {
            Column {
                Text(
                    text = R.string.have_questions.string,
                    color = MafraqTheme.colors.contentPrimary,
                    style = MafraqTheme.typography.titleMedium
                )
                TextIcon(
                    text = R.string.chat_with_support.string,
                    style = MafraqTheme.typography.titleSmall,
                    icon = R.drawable.ic_forward_arrow.painter,
                    onClick = onClick,
                )
            }

            Image(
                painter = R.drawable.chat.painter,
                contentDescription = null,
                modifier = Modifier.size(56.dp)
            )
        }
    )
}