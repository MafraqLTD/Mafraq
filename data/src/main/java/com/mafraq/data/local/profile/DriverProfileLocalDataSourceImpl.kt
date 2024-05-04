package com.mafraq.data.local.profile

import android.content.SharedPreferences
import com.altaie.prettycode.core.utils.extenstions.fromJson
import com.altaie.prettycode.core.utils.extenstions.toJson
import com.mafraq.data.entities.map.Driver
import com.mafraq.data.utils.delete
import com.mafraq.data.utils.putString
import javax.inject.Inject


class DriverProfileLocalDataSourceImpl @Inject constructor(
    private val sharedPref: SharedPreferences
) : ProfileLocalDataSource<Driver> {

    override fun save(value: Driver) =
        sharedPref.putString(key, value.toJson())

    override fun delete() = sharedPref.delete(key)

    override fun get(): Driver? {
        val userJson = sharedPref.getString(key, null)
        return runCatching { userJson.fromJson<Driver>() }.getOrNull()
    }

    override fun isProfileFilled(): Boolean = (get() != null)

    override val key: String = USER_PROFILE_KEY

    companion object {
        private const val USER_PROFILE_KEY = "USER_PROFILE_KEY"
    }
}
