package com.mafraq.data.remote.dataSource.subscription.employee

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.mafraq.data.entities.Session
import com.mafraq.data.entities.Subscriber
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.local.driver.DriverLocalDataSource
import com.mafraq.data.local.profile.ProfileLocalDataSource
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.remote.dataSource.chat.asFlow
import com.mafraq.data.remote.dataSource.chat.delete
import com.mafraq.data.remote.dataSource.chat.insert
import com.mafraq.data.remote.mappers.SubscriberFromEmployeeMapper
import com.mafraq.data.remote.mappers.SubscriberFromRemoteMapper
import com.mafraq.data.remote.models.SubscriberRemote
import com.mafraq.data.repository.chat.group.GroupChatRepositoryImpl.Companion.DRIVERS_COLLECTION
import com.mafraq.data.repository.chat.group.GroupChatRepositoryImpl.Companion.MEMBERS_COLLECTION
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


class EmployeeSubscriptionDataSourceImpl @Inject constructor(
    firestore: FirebaseFirestore,
    private val driverLocalDataSource: DriverLocalDataSource,
    private val profileLocalDataSource: ProfileLocalDataSource<Employee>,
    private val sessionLocalDataSource: SessionLocalDataSource,
    private val subscriberFromRemoteMapper: SubscriberFromRemoteMapper,
    private val subscriberFromEmployeeMapper: SubscriberFromEmployeeMapper,
) : EmployeeSubscriptionDataSource {
    override var driver: Driver? = driverLocalDataSource.get()
    private var session: Session? = sessionLocalDataSource.get()
    private val root by lazy { firestore.collection(DRIVERS_COLLECTION) }
    private val flow = MutableStateFlow(SubscribeRequestStatus.Idle)
    override val subscribeRequestStatusFlow: MutableStateFlow<SubscribeRequestStatus>
        get() = flow.also { reload() }

    private var scope: CoroutineScope? = null
    private lateinit var memberCollection: CollectionReference

    override suspend fun request(driver: Driver) {
        this.driver = driver
        val subscriber = getSubscriber().copy(status = SubscribeRequestStatus.Pending)
        initializeMemberCollection(driver)
        driverLocalDataSource.save(driver)
        memberCollection.insert(
            id = subscriber.email,
            entry = subscriber
        )

        subscribeRequestStatusFlow.emit(subscriber.status)
        observeRequestStatus(subscriber)
    }

    override suspend fun cancel() {
        val subscriber = getSubscriber()
        memberCollection.delete(id = subscriber.email)
        cleanUp()
    }

    private fun getSubscriber(): Subscriber {
        val employee = profileLocalDataSource.get() ?: Employee()
        return subscriberFromEmployeeMapper.map(employee)
    }

    private fun observeRequestStatus(subscriber: Subscriber) {
        scope = CoroutineScope(Dispatchers.IO)
        val memberFlow = memberCollection.asFlow<SubscriberRemote>()
            .map(subscriberFromRemoteMapper::mapList)
            .map { subscribers ->
                subscribers.find { it.email == subscriber.email }
            }

        scope?.launch {
            memberFlow.collect {
                when (it?.status) {
                    null -> {
                        subscribeRequestStatusFlow.emit(SubscribeRequestStatus.Cancelled)
                        cleanUp()
                    }

                    SubscribeRequestStatus.Accepted -> {
                        subscribeRequestStatusFlow.emit(it.status)
                        Timber.i("SUBSCRIPTION")
                        sessionLocalDataSource.save(
                            driverEmail = driver?.email ?: session?.driverEmail,
                            email = subscriber.email
                        )
                        cleanUp()
                    }

                    else -> Unit
                }
            }
        }
    }

    private fun initializeMemberCollection(driver: Driver) {
        if (::memberCollection.isInitialized.not())
            memberCollection = root.document(driver.email)
                .collection(MEMBERS_COLLECTION)
    }

    private fun cleanUp() {
        driver = null
        scope?.cancel()
        scope = null
    }

    private fun reload() {
        cleanUp()
        driver = driverLocalDataSource.get()
        session = sessionLocalDataSource.get()
        driver?.let {
            initializeMemberCollection(it)
            observeRequestStatus(subscriber = getSubscriber())
        } ?: session?.driverEmail?.run {
            initializeMemberCollection(Driver(email = this))
            observeRequestStatus(subscriber = getSubscriber())
        }
    }

}
