package com.mafraq.data.local.session

import android.content.SharedPreferences
import com.altaie.prettycode.core.utils.extenstions.fromJson
import com.altaie.prettycode.core.utils.extenstions.toJson
import com.mafraq.data.entities.Session
import com.mafraq.data.utils.delete
import com.mafraq.data.utils.putString
import javax.inject.Inject


class SessionLocalDataSourceImpl @Inject constructor(
    private val sharedPref: SharedPreferences
) : SessionLocalDataSource {

    override fun save(driverEmail: String?, email: String?) {
        val session = Session(
            driverEmail = driverEmail,
            email = email,
        )
        sharedPref.putString(key, session.toJson())
    }

    override fun delete() = sharedPref.delete(key)

    override fun get(): Session? {
        val userJson = sharedPref.getString(key, null)
        return runCatching { userJson.fromJson<Session>() }.getOrNull()
    }

    override fun isFirstLaunch(): Boolean = with(FIRST_LAUNCH_KEY) {
        return sharedPref.getString(this, null)?.let { false }
            ?: sharedPref.putString(key = this, value = this).let { true }
    }

    override val key: String = SESSION_KEY

    companion object {
        private const val SESSION_KEY = "SESSION_KEY"
        private const val FIRST_LAUNCH_KEY = "FIRST_LAUNCH_KEY"
    }
}
