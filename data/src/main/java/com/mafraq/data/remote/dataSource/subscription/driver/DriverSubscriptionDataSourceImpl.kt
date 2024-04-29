package com.mafraq.data.remote.dataSource.subscription.driver

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.mafraq.data.entities.Session
import com.mafraq.data.entities.Subscriber
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.remote.dataSource.chat.asFlow
import com.mafraq.data.remote.dataSource.chat.delete
import com.mafraq.data.remote.dataSource.chat.insert
import com.mafraq.data.repository.chat.group.GroupChatRepositoryImpl.Companion.DRIVERS_COLLECTION
import com.mafraq.data.repository.chat.group.GroupChatRepositoryImpl.Companion.MEMBERS_COLLECTION
import com.mafraq.data.repository.chat.group.GroupChatRepositoryImpl.Companion.PENDING_COLLECTION
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class DriverSubscriptionDataSourceImpl @Inject constructor(
    firestore: FirebaseFirestore,
    sessionLocalDataSource: SessionLocalDataSource,
) : DriverSubscriptionDataSource {
    private val session: Session? = sessionLocalDataSource.get()
    private val driverId = requireNotNull(session?.driverId)
    private val root by lazy {
        firestore
            .collection(DRIVERS_COLLECTION)
            .document(driverId)
    }

    private val membersCollection: CollectionReference by lazy {
        root.collection(MEMBERS_COLLECTION)
    }

    private val pendingCollection: CollectionReference by lazy {
        root.collection(PENDING_COLLECTION)
    }

    override val membersFlow: Flow<List<Subscriber>> by lazy {
        membersCollection.asFlow<Subscriber>()
    }

    override val pendingFlow: Flow<List<Subscriber>> by lazy {
        pendingCollection.asFlow<Subscriber>()
    }

    override suspend fun cancel(subscriber: Subscriber) {
        pendingCollection.delete(subscriber.id)
    }

    override suspend fun accept(subscriber: Subscriber) {
        cancel(subscriber)
        membersCollection.insert(id = subscriber.id, entry = subscriber)
    }
}