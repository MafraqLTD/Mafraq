package com.mafraq

import android.app.Application
import com.mapbox.common.MapboxOptions
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MafraqApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        MapboxOptions.accessToken = BuildConfig.MAPBOX_TOKEN
    }
}
