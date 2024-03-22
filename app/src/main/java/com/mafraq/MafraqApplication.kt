package com.mafraq

import android.app.Application
import com.google.android.gms.maps.MapsInitializer
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MafraqApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        MapsInitializer.initialize(applicationContext)
    }
}
