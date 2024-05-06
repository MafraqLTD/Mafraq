package com.mafraq.data.remote.dataSource.subscription.driver

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.mafraq.data.entities.Session
import com.mafraq.data.entities.Subscriber
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.remote.dataSource.chat.asFlow
import com.mafraq.data.remote.dataSource.chat.delete
import com.mafraq.data.remote.dataSource.chat.insert
import com.mafraq.data.remote.dataSource.subscription.employee.SubscribeRequestStatus
import com.mafraq.data.remote.mappers.SubscriberFromRemoteMapper
import com.mafraq.data.remote.models.SubscriberRemote
import com.mafraq.data.repository.chat.group.GroupChatRepositoryImpl.Companion.DRIVERS_COLLECTION
import com.mafraq.data.repository.chat.group.GroupChatRepositoryImpl.Companion.MEMBERS_COLLECTION
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.asDeferred
import javax.inject.Inject


class DriverSubscriptionDataSourceImpl @Inject constructor(
    firestore: FirebaseFirestore,
    sessionLocalDataSource: SessionLocalDataSource,
    private val subscriberFromRemoteMapper: SubscriberFromRemoteMapper
) : DriverSubscriptionDataSource {
    private val session: Session? = sessionLocalDataSource.get()
    private val root by lazy { firestore.collection(DRIVERS_COLLECTION) }

    private val membersCollection: CollectionReference
        get() = session?.driverEmail?.let {
            root.document(it).collection(MEMBERS_COLLECTION)
        } ?: root

    override val allMembersFlow: Flow<List<Subscriber>>
        get() = membersCollection.asFlow<SubscriberRemote>()
            .map(subscriberFromRemoteMapper::mapList)

    override val subscribersFlow: Flow<List<Subscriber>>
        get() = allMembersFlow.map { subscribers ->
            subscribers.filter { it.status.isAccepted }
        }

    override val pendingFlow: Flow<List<Subscriber>>
        get() = allMembersFlow.map { subscribers ->
            subscribers.filter { it.status.isPending }
        }

    override suspend fun unsubscribe(subscriber: Subscriber) {
        membersCollection.delete(subscriber.email)
    }

    override suspend fun cancel(subscriber: Subscriber) {
        membersCollection.delete(subscriber.email)
    }

    override suspend fun accept(subscriber: Subscriber) {
        membersCollection.insert(
            id = subscriber.email,
            entry = subscriber.copy(status = SubscribeRequestStatus.Accepted)
        )
    }
}
