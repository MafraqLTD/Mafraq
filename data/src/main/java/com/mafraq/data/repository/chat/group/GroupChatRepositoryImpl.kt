package com.mafraq.data.repository.chat.group

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.mafraq.data.entities.Session
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.remote.mappers.MessageFromRemoteMapper
import com.mafraq.data.remote.mappers.MessageToRemoteMapper
import javax.inject.Inject


class GroupChatRepositoryImpl @Inject constructor(
    firestore: FirebaseFirestore,
    sessionLocalDataSource: SessionLocalDataSource,
    messageToRemoteMapper: MessageToRemoteMapper,
    messageFromRemoteMapper: MessageFromRemoteMapper,
) : GroupChatRepository(messageToRemoteMapper, messageFromRemoteMapper) {
    private val session: Session? = sessionLocalDataSource.get()
    override val chatCollection: CollectionReference by lazy {
        firestore
            .collection(DRIVERS_COLLECTION)
            .document(requireNotNull(session?.driverId))
//            .collection(SUBSCRIPTIONS_COLLECTION)
//            .document(requireNotNull(session?.subscriptionId))
            .collection(MESSAGES_COLLECTION)
    }

    companion object {
        const val DRIVERS_COLLECTION = "Drivers"
        const val MESSAGES_COLLECTION = "Messages"
        const val MEMBERS_COLLECTION = "Members"
        const val PENDING_COLLECTION = "Pending"
//        const val SUBSCRIPTIONS_COLLECTION = "Subscriptions"
    }
}
