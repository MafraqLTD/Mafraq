package com.mafraq.presentation.features.chatSupport.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mafraq.presentation.R
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.utils.extensions.painter

@Composable
fun MessageItem(
    message: String,
    receiveDate: String,
    isRead: Boolean,
    isFromMe: Boolean,
) {
    val receiveIcon = if (isRead) R.drawable.ic_read else R.drawable.ic_unread
    val alignment = if (isFromMe) Alignment.CenterEnd else Alignment.CenterStart
    val containerColor = if (isFromMe) MafraqTheme.colors.primary else MaterialTheme.colorScheme.surfaceVariant
    Box(Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier.align(alignment),
            colors = CardDefaults.cardColors(containerColor = containerColor)
        ) {
            Column(
                modifier = Modifier
                    .padding(MafraqTheme.sizes.small)
                    .wrapContentSize(),
                verticalArrangement = Arrangement.spacedBy(MafraqTheme.sizes.extraSmall)
            ) {
                Text(text = message)

                Row(
                    modifier = Modifier.align(Alignment.End),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = receiveDate,
                        style = MafraqTheme.typography.label,
                    )

                    Icon(
                        painter = receiveIcon.painter,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}