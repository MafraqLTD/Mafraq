@file:Suppress("UnstableApiUsage")

import com.gateway.buildscr.Config
import com.gateway.buildscr.Config.Version

plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.mafraq.data"
    compileSdk = Version.COMPILE_SDK
    buildToolsVersion = Version.BUILD_TOOLS

    defaultConfig {
        minSdk = Version.MIN_SDK

        testInstrumentationRunner = Config.ANDROID_TEST_INSTRUMENTATION
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Version.JVM
        targetCompatibility = Version.JVM
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
    kapt(libs.hilt.compiler)
    androidTestImplementation(libs.bundles.androidTestImplementation)
}

kapt { correctErrorTypes = true }
