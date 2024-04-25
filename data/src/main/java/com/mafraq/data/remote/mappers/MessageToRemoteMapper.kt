package com.mafraq.data.remote.mappers

import com.altaie.prettycode.core.mapper.base.Mapper
import com.mafraq.data.entities.chat.Message
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.remote.models.chat.MessageRemote
import javax.inject.Inject


class MessageToRemoteMapper @Inject constructor(
    sessionLocalDataSource: SessionLocalDataSource
) : Mapper<Message, MessageRemote> {
    private val userId = requireNotNull(sessionLocalDataSource.get()?.userId)

    override fun map(from: Message): MessageRemote = from.run {
        MessageRemote(
            id = id,
            content = content,
            senderName = senderName,
            senderImageUrl = senderImageUrl,
            read = isRead,
            senderId = userId,
        )
    }
}
