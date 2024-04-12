package com.mafraq.data.repository.chat.support

import com.mafraq.data.entities.chat.ChatMember
import com.mafraq.data.remote.dataSource.chat.FirebaseFireStoreMessenger
import com.mafraq.data.remote.mappers.MessageFromRemoteMapper
import com.mafraq.data.remote.mappers.MessageToRemoteMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


abstract class SupportChatRepository(
    messageToRemoteMapper: MessageToRemoteMapper,
    messageFromRemoteMapper: MessageFromRemoteMapper,
) : FirebaseFireStoreMessenger(messageToRemoteMapper, messageFromRemoteMapper) {
    abstract val chatMemberStateFlow: Flow<ChatMember>
}
