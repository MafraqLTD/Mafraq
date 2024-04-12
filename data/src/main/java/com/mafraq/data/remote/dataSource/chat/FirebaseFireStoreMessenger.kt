package com.mafraq.data.remote.dataSource.chat


import com.google.firebase.firestore.CollectionReference
import com.mafraq.data.entities.chat.Message
import com.mafraq.data.remote.mappers.MessageFromRemoteMapper
import com.mafraq.data.remote.mappers.MessageToRemoteMapper
import com.mafraq.data.remote.models.chat.MessageRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


abstract class FirebaseFireStoreMessenger(
    private val messageToRemoteMapper: MessageToRemoteMapper,
    private val messageFromRemoteMapper: MessageFromRemoteMapper,
) {
    val chatFlow: Flow<List<Message>> by lazy {
        chatCollection.asFlow<MessageRemote>().map { messages ->
            messages.map(messageFromRemoteMapper::map)
        }
    }

    abstract val chatCollection: CollectionReference

    suspend fun fetchMessages(): List<Message> =
        chatCollection.fetchAll<MessageRemote>()
            .map(messageFromRemoteMapper::map)

    suspend fun sendMessage(message: Message): Boolean {
        val messageRemote = messageToRemoteMapper.map(message)
        return chatCollection.insert(messageRemote, messageRemote.id)
    }

    suspend fun editMessage(message: Message): Boolean {
        val messageRemote = messageToRemoteMapper.map(message)
        return chatCollection.insert(messageRemote, messageRemote.id)
    }

    suspend fun deleteMessage(messageId: String): Boolean =
        chatCollection.delete(messageId)
}
