package com.mafraq.data.remote.dataSource.subscription.employee

import com.google.firebase.firestore.FirebaseFirestore
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.local.driver.DriverLocalDataSource
import com.mafraq.data.local.driver.DriverLocalDataSourceImpl
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
    subscriberFromEmployeeMapper: SubscriberFromEmployeeMapper,
    private val driverLocalDataSource: DriverLocalDataSource
) : EmployeeSubscriptionDataSource {
    override var driver: Driver? = driverLocalDataSource.get()
    private val employee = requireNotNull(profileLocalDataSource.get())
    private val subscriber = subscriberFromEmployeeMapper.map(employee)
    private val root by lazy {
        firestore.collection(DRIVERS_COLLECTION)
    }

    override suspend fun request(driver: Driver) {
        driverLocalDataSource.save(driver)
        this.driver = driver
        root.document(driver.id)
            .collection(PENDING_COLLECTION)
            .insert(id = subscriber.id, entry = subscriber)
    }

    override suspend fun cancel() {
        root.document(requireNotNull(driver?.id))
            .collection(PENDING_COLLECTION)
            .delete(id = subscriber.id)
        this.driver = null
        driverLocalDataSource.delete()
    }
}
