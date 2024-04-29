package com.mafraq.data.local.driver

import android.content.SharedPreferences
import com.altaie.prettycode.core.utils.extenstions.fromJson
import com.altaie.prettycode.core.utils.extenstions.toJson
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.local.profile.ProfileLocalDataSource
import com.mafraq.data.utils.delete
import com.mafraq.data.utils.putString
import javax.inject.Inject


class DriverLocalDataSourceImpl @Inject constructor(
    private val sharedPref: SharedPreferences
) : DriverLocalDataSource {

    override fun save(value: Driver) =
        sharedPref.putString(key, value.toJson())

    override fun delete() = sharedPref.delete(key)

    override fun get(): Driver? {
        val userJson = sharedPref.getString(key, null)
        return runCatching { userJson.fromJson<Driver>() }.getOrNull()
    }

    override val key: String = USER_PROFILE_KEY

    companion object {
        private const val USER_PROFILE_KEY = "DRIVER_KEY"
    }
}
