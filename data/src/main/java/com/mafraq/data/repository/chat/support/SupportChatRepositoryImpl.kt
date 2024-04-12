package com.mafraq.data.repository.chat.support

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.mafraq.data.entities.Session
import com.mafraq.data.entities.chat.ChatMember
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.remote.dataSource.chat.asFlow
import com.mafraq.data.remote.mappers.MessageFromRemoteMapper
import com.mafraq.data.remote.mappers.MessageToRemoteMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SupportChatRepositoryImpl @Inject constructor(
    firestore: FirebaseFirestore,
    sessionLocalDataSource: SessionLocalDataSource,
    messageToRemoteMapper: MessageToRemoteMapper,
    messageFromRemoteMapper: MessageFromRemoteMapper,
) : SupportChatRepository(messageToRemoteMapper, messageFromRemoteMapper) {
    private val session: Session? = sessionLocalDataSource.get()

    override val chatCollection: CollectionReference by lazy {
        firestore.collection(SUPPORT_CHAT_COLLECTION)
            .document(requireNotNull(session?.userId))
            .collection(MESSAGES_COLLECTION)
    }

    private val supportDocument: DocumentReference by lazy {
        firestore.collection(SUPPORT_CHAT_COLLECTION)
            .document(SUPPORT_DOCUMENT)
    }

    override val chatMemberStateFlow: Flow<ChatMember> by lazy {
        supportDocument.asFlow()
    }

    private companion object {
        const val SUPPORT_CHAT_COLLECTION = "Support"
        const val MESSAGES_COLLECTION = "Messages"
        const val SUPPORT_DOCUMENT = "Admin"
    }
}
