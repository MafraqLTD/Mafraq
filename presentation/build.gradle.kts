@file:Suppress("UnstableApiUsage")

import com.gateway.buildscr.Config.Version
import com.gateway.buildscr.applyConfiguration
import com.gateway.buildscr.ignoreExperimentalWarnings

plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.hilt.get().pluginId)
}

android {
    namespace = "com.mafraq.presentation"

    applyConfiguration(enableCompose = true)

    kotlinOptions {
        jvmTarget = Version.JVM.toString()
        ignoreExperimentalWarnings()
    }
}

kotlin {
    jvmToolchain(Version.JVM.majorVersion.toInt())
}

dependencies {
    implementation(projects.data)
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.implementation.compose)
    implementation(libs.bundles.implementation.androidx)
    implementation(libs.bundles.implementation.utils)
    kapt(libs.hilt.compiler)
    debugImplementation(platform(libs.compose.bom))
    debugImplementation(libs.bundles.debugImplementation.compose)
    androidTestImplementation(libs.bundles.androidTestImplementation)
    testImplementation(libs.test.junit)
}

kapt { correctErrorTypes = true }
