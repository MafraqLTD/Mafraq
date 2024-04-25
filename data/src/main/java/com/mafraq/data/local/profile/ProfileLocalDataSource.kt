package com.mafraq.data.local.profile

import com.mafraq.data.entities.profile.Employee


interface ProfileLocalDataSource {

    fun save(value: Employee)

    fun delete()

    fun get(): Employee?

    fun isProfileFilled(): Boolean

    val key: String
}
