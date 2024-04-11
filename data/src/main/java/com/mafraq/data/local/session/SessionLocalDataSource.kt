package com.mafraq.data.local.session

import com.mafraq.data.entities.Session


interface SessionLocalDataSource {

    fun save(driverId: String?, userId: String?, subscriptionId: String?)

    fun delete()

    fun get(): Session?

    fun isFirstLaunch(): Boolean

    val key: String
}
