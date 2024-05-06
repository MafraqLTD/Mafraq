package com.mafraq.employee

import android.app.Application
import com.google.android.libraries.places.api.Places
import com.mapbox.common.MapboxOptions
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.Locale


@HiltAndroidApp
class MafraqApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        timberConfig()
        Places.initialize(
            /* applicationContext = */ applicationContext,
            /* apiKey = */ BuildConfig.PLACES_API_KEY,
            /* locale = */ Locale.getDefault()
        )
        MapboxOptions.accessToken = BuildConfig.MAPBOX_TOKEN
    }


    private fun timberConfig() {
        if (BuildConfig.DEBUG)
            Timber.plant(object : Timber.DebugTree() {
                /**
                 * Override [log] to modify the tag and add a "global tag" prefix to it. You can rename the String "global_tag_" as you see fit.
                 */
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    super.log(priority, "DEBUGGING", "$tag -> $message", t)
                }

                /**
                 * Override [createStackElementTag] to include a add a "method name" to the tag.
                 */
                override fun createStackElementTag(element: StackTraceElement): String {
                    return String.format(
                        "%s:%s$%s()",
                        element.fileName,
                        element.lineNumber,
                        element.methodName,
                    )
                }
            })
    }
}
