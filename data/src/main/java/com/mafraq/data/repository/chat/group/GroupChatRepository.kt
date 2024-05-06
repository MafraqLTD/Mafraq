package com.mafraq.data.repository.chat.group

import com.mafraq.data.entities.chat.GroupChatState
import com.mafraq.data.remote.dataSource.chat.FirebaseFireStoreMessenger
import com.mafraq.data.remote.mappers.MessageFromRemoteMapper
import com.mafraq.data.remote.mappers.MessageToRemoteMapper
import kotlinx.coroutines.flow.Flow


abstract class GroupChatRepository(
    messageToRemoteMapper: MessageToRemoteMapper,
    messageFromRemoteMapper: MessageFromRemoteMapper,
) : FirebaseFireStoreMessenger(messageToRemoteMapper, messageFromRemoteMapper) {
    abstract val stateFlow: Flow<GroupChatState>
}
