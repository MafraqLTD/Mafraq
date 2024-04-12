package com.mafraq.presentation.features.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.firebase.Timestamp
import com.mafraq.data.remote.models.chat.MessageRemote
import com.mafraq.data.utils.toFormattedDateTime
import com.mafraq.presentation.R
import com.mafraq.presentation.design.components.ColumnPreview
import com.mafraq.presentation.design.components.Spacer
import com.mafraq.presentation.design.theme.MafraqTheme
import com.mafraq.presentation.design.theme.MafraqTheme.colors
import com.mafraq.presentation.design.theme.MafraqTheme.sizes
import com.mafraq.presentation.design.theme.MafraqTheme.typography
import com.mafraq.presentation.utils.extensions.firstOrEmpty
import com.mafraq.presentation.utils.extensions.painter


@Composable
fun MessageItem(
    messageRemote: MessageRemote,
    showSender: Boolean = false,
    onClick: () -> Unit = {}
) {
    val alignment = if (messageRemote.isFromMe) Alignment.CenterEnd else Alignment.CenterStart
    Box(Modifier.fillMaxWidth()) {
        val modifier = Modifier.align(alignment)

        if (showSender && !messageRemote.isFromMe)
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.Bottom
            ) {
                if (messageRemote.senderImageUrl.isNotEmpty())
                    AsyncImage(
                        model = messageRemote.senderImageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(sizes.large)
                            .clip(CircleShape)
                    )
                else
                    FirstCharAvatarReplacement(text = messageRemote.senderName)

                Spacer.ExtraSmall(vertical = false)

                Content(messageRemote = messageRemote, showSender = true, onClick = onClick)
            }
        else
            Content(messageRemote = messageRemote, modifier = modifier, onClick = onClick)
    }
}


@Composable
private fun Content(
    messageRemote: MessageRemote,
    modifier: Modifier = Modifier,
    showSender: Boolean = false,
    onClick: () -> Unit = {},
) {
    val receiveIcon = if (messageRemote.isRead) R.drawable.ic_read else R.drawable.ic_unread
    val containerColor = if (messageRemote.isFromMe) colors.primary else colorScheme.surfaceVariant
    val senderNameColor = if (messageRemote.isFromMe) colorScheme.surfaceVariant else colors.primary

    Card(
        modifier = modifier,
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Column(
            modifier = Modifier
                .padding(MafraqTheme.sizes.small)
                .wrapContentSize(),
            verticalArrangement = Arrangement.spacedBy(MafraqTheme.sizes.extraSmall)
        ) {
            if (showSender)
                Text(
                    text = messageRemote.senderName,
                    style = typography.label,
                    color = senderNameColor
                )

            Text(text = messageRemote.content)

            Row(
                modifier = Modifier.align(Alignment.End),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = messageRemote.timestamp.toFormattedDateTime(),
                    style = typography.label,
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

@Composable
private fun FirstCharAvatarReplacement(
    text: String,
    size: Dp = 32.dp,
    shape: Shape = CircleShape,
    alignment: Alignment = Alignment.Center,
    style: TextStyle = typography.titleMedium,
    contentColor: Color = colorScheme.onPrimary,
    containerColor: Color = colorScheme.primary,
) {
    Box(
        contentAlignment = alignment,
        modifier = Modifier
            .size(size)
            .clip(shape)
            .background(containerColor)
    ) {
        Text(
            text = text.firstOrEmpty().uppercase(),
            color = contentColor,
            style = style
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun Preview() = ColumnPreview {

    MessageItem(
        messageRemote = MessageRemote(
            content = "Hello, World!",
            timestamp = Timestamp.now(),
            read = false,
            fromMe = true,
            senderName = "Ahmed Mones",
            senderImageUrl = "https://picsum.photos/200/300",
        ),
        showSender = false,
    )

    MessageItem(
        messageRemote = MessageRemote(
            content = "Hello, World!",
            timestamp = Timestamp.now(),
            read = false,
            fromMe = false,
            senderName = "Ahmed Mones",
            senderImageUrl = "https://picsum.photos/200/300",
        ),
        showSender = true,
    )
}
