package com.mafraq.data.remote.dataSource.subscription.employee

import com.google.firebase.firestore.FirebaseFirestore
import com.mafraq.data.local.profile.ProfileLocalDataSource
import com.mafraq.data.remote.dataSource.chat.delete
import com.mafraq.data.remote.dataSource.chat.insert
import com.mafraq.data.remote.mappers.SubscriberFromEmployeeMapper
import com.mafraq.data.repository.chat.group.GroupChatRepositoryImpl.Companion.DRIVERS_COLLECTION
import com.mafraq.data.repository.chat.group.GroupChatRepositoryImpl.Companion.PENDING_COLLECTION
import javax.inject.Inject


class EmployeeSubscriptionDataSourceImpl @Inject constructor(
    firestore: FirebaseFirestore,
    profileLocalDataSource: ProfileLocalDataSource,
    subscriberFromEmployeeMapper: SubscriberFromEmployeeMapper
) : EmployeeSubscriptionDataSource {
    private val employee = requireNotNull(profileLocalDataSource.get())
    private val subscriber = subscriberFromEmployeeMapper.map(employee)
    private val root by lazy {
        firestore.collection(DRIVERS_COLLECTION)
    }

    override suspend fun request(driverId: String) {
       root.document(driverId)
           .collection(PENDING_COLLECTION)
        .insert(id = subscriber.id, entry = subscriber)
    }

    override suspend fun cancel(driverId: String) {
        root.document(driverId)
            .collection(PENDING_COLLECTION)
            .delete(id = subscriber.id)
    }
}
