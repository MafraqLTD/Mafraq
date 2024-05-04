package com.mafraq.data.local.profile


interface ProfileLocalDataSource<T> {

    fun save(value: T)

    fun delete()

    fun get(): T?

    fun isProfileFilled(): Boolean

    val key: String
}
