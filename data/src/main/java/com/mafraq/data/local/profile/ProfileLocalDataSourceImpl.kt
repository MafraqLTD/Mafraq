package com.mafraq.data.local.profile

import android.content.SharedPreferences
import com.altaie.prettycode.core.utils.extenstions.fromJson
import com.altaie.prettycode.core.utils.extenstions.toJson
import com.mafraq.data.entities.profile.Employee
import com.mafraq.data.utils.delete
import com.mafraq.data.utils.putString
import javax.inject.Inject


class ProfileLocalDataSourceImpl @Inject constructor(
    private val sharedPref: SharedPreferences
) : ProfileLocalDataSource {

    override fun save(value: Employee) =
        sharedPref.putString(key, value.toJson())

    override fun delete() = sharedPref.delete(key)

    override fun get(): Employee? {
        val userJson = sharedPref.getString(key, null)
        return runCatching { userJson.fromJson<Employee>() }.getOrNull()
    }

    override fun isProfileFilled(): Boolean = (get() != null)

    override val key: String = USER_PROFILE_KEY

    companion object {
        private const val USER_PROFILE_KEY = "USER_PROFILE_KEY"
    }
}
