package com.mafraq.data.repository.chat.group

import com.mafraq.data.remote.dataSource.chat.FirebaseFireStoreMessenger
import com.mafraq.data.remote.mappers.MessageFromRemoteMapper
import com.mafraq.data.remote.mappers.MessageToRemoteMapper


abstract class GroupChatRepository(
    messageToRemoteMapper: MessageToRemoteMapper,
    messageFromRemoteMapper: MessageFromRemoteMapper,
) : FirebaseFireStoreMessenger(messageToRemoteMapper, messageFromRemoteMapper)
