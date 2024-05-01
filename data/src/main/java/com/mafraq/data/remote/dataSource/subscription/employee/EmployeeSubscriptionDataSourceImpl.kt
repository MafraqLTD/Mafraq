package com.mafraq.data.remote.dataSource.subscription.employee

import com.google.firebase.firestore.FirebaseFirestore
import com.mafraq.data.entities.Subscriber
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.local.driver.DriverLocalDataSource
import com.mafraq.data.local.profile.ProfileLocalDataSource
import com.mafraq.data.remote.dataSource.chat.delete
import com.mafraq.data.remote.dataSource.chat.insert
import com.mafraq.data.remote.mappers.SubscriberFromEmployeeMapper
import com.mafraq.data.repository.chat.group.GroupChatRepositoryImpl.Companion.DRIVERS_COLLECTION
import com.mafraq.data.repository.chat.group.GroupChatRepositoryImpl.Companion.PENDING_COLLECTION
import javax.inject.Inject


class EmployeeSubscriptionDataSourceImpl @Inject constructor(
    firestore: FirebaseFirestore,
    private val driverLocalDataSource: DriverLocalDataSource,
    private val profileLocalDataSource: ProfileLocalDataSource,
    private val subscriberFromEmployeeMapper: SubscriberFromEmployeeMapper,
) : EmployeeSubscriptionDataSource {
    override var driver: Driver? = driverLocalDataSource.get()
    private val root by lazy { firestore.collection(DRIVERS_COLLECTION) }

    override suspend fun request(driver: Driver) {
        this.driver = driver
        val subscriber = getSubscriber()
        driverLocalDataSource.save(driver)
        root.document(driver.email)
            .collection(PENDING_COLLECTION)
            .insert(id = subscriber.email, entry = subscriber)
    }

    override suspend fun cancel() {
        val subscriber = getSubscriber()
        root.document(requireNotNull(driver?.email))
            .collection(PENDING_COLLECTION)
            .delete(id = subscriber.email)
        this.driver = null
        driverLocalDataSource.delete()
    }

    private fun getSubscriber(): Subscriber {
        val employee = requireNotNull(profileLocalDataSource.get())
        return subscriberFromEmployeeMapper.map(employee)
    }
}
