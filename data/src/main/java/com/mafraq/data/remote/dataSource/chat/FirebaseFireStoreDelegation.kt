package com.mafraq.data.remote.dataSource.chat


import com.google.firebase.firestore.CollectionReference
import com.mafraq.data.entities.chat.Message
import kotlinx.coroutines.flow.Flow


interface FirebaseFireStoreDelegation {
    val chatFlow: Flow<List<Message>>
    val chatCollection: CollectionReference
    suspend fun fetchMessages(): List<Message>
    suspend fun editMessage(message: Message): Boolean
    suspend fun sendMessage(message: Message): Boolean
    suspend fun deleteMessage(messageId: String): Boolean
}
