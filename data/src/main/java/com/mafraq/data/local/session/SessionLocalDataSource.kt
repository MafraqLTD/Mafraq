package com.mafraq.data.local.session

import com.mafraq.data.entities.Session


interface SessionLocalDataSource {

    fun save(driverEmail: String?, email: String?)

    fun delete()

    fun get(): Session?

    fun isFirstLaunch(): Boolean

    val key: String
}
