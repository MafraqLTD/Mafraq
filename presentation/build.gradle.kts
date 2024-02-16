@file:Suppress("UnstableApiUsage")

import com.gateway.buildscr.Config
import com.gateway.buildscr.Config.Version

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.mafraq.presentation"
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

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Version.COMPOSE_COMPILER
    }

    kotlinOptions {
        jvmTarget = Version.JVM.toString()
        freeCompilerArgs =
            freeCompilerArgs + listOf(
                "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
                "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
                "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                "-opt-in=androidx.compose.foundation.layout.ExperimentalLayoutApi",
                "-opt-in=com.google.accompanist.permissions.ExperimentalPermissionsApi",
                "-opt-in=androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi",
            )
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
}

kapt { correctErrorTypes = true }
