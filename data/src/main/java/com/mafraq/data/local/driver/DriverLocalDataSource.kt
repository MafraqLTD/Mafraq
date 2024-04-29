package com.mafraq.data.local.driver

import com.mafraq.data.entities.map.Driver


interface DriverLocalDataSource {

    fun save(value: Driver)

    fun delete()

    fun get(): Driver?

    val key: String
}
