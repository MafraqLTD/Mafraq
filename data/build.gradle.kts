@file:Suppress("UnstableApiUsage")

import com.gateway.buildscr.Config
import com.gateway.buildscr.Config.Version
import com.gateway.buildscr.applyConfiguration
import com.gateway.buildscr.buildConfigField

plugins {
    id(libs.plugins.kotlin.serialization.get().pluginId)
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.hilt.get().pluginId)
}

android {
    namespace = "com.mafraq.data"

    applyConfiguration {
        buildConfigField<String>(key = "BASE_URL")
        buildConfigField<String>(key = "MAPBOX_TOKEN")
        buildConfigField<String>(key = "ROUTES_API_KEY")
        buildConfigField<String>(key = "GEOCODING_API_KEY")
        buildConfigField<String>(key = "RETABLE_API_KEY")
        buildConfigField<String>(key = "ROUTES_BASE_URL")
        buildConfigField<String>(key = "RETABLE_ADS_TABLE_ID")
        buildConfigField<String>(key = "RETABLE_DRIVER_TABLE_ID")
        buildConfigField<String>(key = "RETABLE_EMPLOYEE_TABLE_ID")
    }

    kotlinOptions {
        jvmTarget = Version.JVM.toString()
    }
}

kotlin {
    jvmToolchain(Version.JVM.majorVersion.toInt())
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.implementation.androidx)
    implementation(libs.bundles.implementation.utils)
    api(libs.bundles.implementation.web)
    kapt(libs.hilt.compiler)
    androidTestImplementation(libs.bundles.androidTestImplementation)
    testImplementation(libs.test.junit)
}

kapt { correctErrorTypes = true }
