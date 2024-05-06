package com.mafraq.data.repository.chat.group

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.mafraq.data.entities.Session
import com.mafraq.data.entities.chat.GroupChatState
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.remote.dataSource.subscription.driver.DriverSubscriptionDataSource
import com.mafraq.data.remote.mappers.MessageFromRemoteMapper
import com.mafraq.data.remote.mappers.MessageToRemoteMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject


class GroupChatRepositoryImpl @Inject constructor(
    firestore: FirebaseFirestore,
    sessionLocalDataSource: SessionLocalDataSource,
    messageToRemoteMapper: MessageToRemoteMapper,
    messageFromRemoteMapper: MessageFromRemoteMapper,
    driverSubscriptionDataSource: DriverSubscriptionDataSource,
) : GroupChatRepository(messageToRemoteMapper, messageFromRemoteMapper) {
    private val session: Session? by lazy { sessionLocalDataSource.get() }
    override val chatCollection: CollectionReference by lazy {
        Timber.d("SESSION: $session")
        firestore
            .collection(DRIVERS_COLLECTION)
            .document(requireNotNull(session?.driverEmail))
            .collection(MESSAGES_COLLECTION)
    }

    override val stateFlow: Flow<GroupChatState> by lazy {
        driverSubscriptionDataSource.allMembersFlow.map { subscribers ->
            Timber.i("SUBSCRIBERS: $subscribers")
            GroupChatState(
                members = subscribers.size + 1,
                activeMembers = subscribers.count { it.active },
                title = subscribers.find {
                    it.workLocation
                        .formattedAddress
                        .isNotEmpty()
                }
                    ?.workLocation
                    ?.formattedAddress
                    .orEmpty(),
            )
        }
    }

    companion object {
        const val DRIVERS_COLLECTION = "Drivers"
        const val MESSAGES_COLLECTION = "Messages"
        const val MEMBERS_COLLECTION = "Members"
    }
}
