package com.mafraq.data.remote.dataSource.chat

import com.google.firebase.firestore.CollectionReference
import com.mafraq.data.entities.chat.Message
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FirebaseFireStoreDelegationImpl @Inject constructor(

): FirebaseFireStoreDelegation {

    override val chatFlow: Flow<List<Message>> by lazy {
        chatCollection.asFlow()
    }
    override val chatCollection: CollectionReference
        get() = error("Not yet implemented")

    override suspend fun fetchMessages(): List<Message> =
        chatCollection.fetchAll()

    override suspend fun sendMessage(message: Message): Boolean =
        chatCollection.insert(message, message.id)

    override suspend fun editMessage(message: Message): Boolean  =
        chatCollection.insert(message, message.id)

    override suspend fun deleteMessage(messageId: String): Boolean =
        chatCollection.delete(messageId)
}
