package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.mafraq.data.entities.chat.Message
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.remote.models.chat.MessageRemote
import com.mafraq.data.utils.toFormattedDateTime
import javax.inject.Inject


class MessageFromRemoteMapper @Inject constructor(
    sessionLocalDataSource: SessionLocalDataSource
) : Mapper<MessageRemote, Message> {
    private val userId = requireNotNull(sessionLocalDataSource.get()?.userId)

    override fun map(from: MessageRemote): Message = from.run {
        Message(
            id = id,
            content = content,
            senderName = senderName,
            senderImageUrl = senderImageUrl,
            isRead = read,
            isFromMe = userId == senderId,
            receivedAt = timestamp.toFormattedDateTime()
        )
    }
}
