@file:Suppress("UnstableApiUsage")

import com.gateway.buildscr.Config
import com.gateway.buildscr.Config.Version
import com.gateway.buildscr.getLocalProperty
import com.gateway.buildscr.buildConfigField
import com.gateway.buildscr.ignoreExperimentalWarnings

import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension

private object LocalConfig {
    const val CODE = 1
    const val NAME = "1.0.${CODE - 1}"
}

private object ProductionConfig {
    const val CODE = 1
    const val NAME = "1.0.${CODE - 1}"
}

plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.google.services.asProvider().get().pluginId)
    id(libs.plugins.google.crashlytics.asProvider().get().pluginId)
    id(libs.plugins.hilt.get().pluginId)
}

android {
    namespace = Config.APPLICATION_ID
    compileSdk = Version.COMPILE_SDK
    buildToolsVersion = Version.BUILD_TOOLS

    defaultConfig {
        applicationId = Config.APPLICATION_ID
        minSdk = Version.MIN_SDK
        targetSdk = Version.TARGET_SDK
        versionCode = LocalConfig.CODE
        versionName = LocalConfig.NAME

        testInstrumentationRunner = Config.ANDROID_TEST_INSTRUMENTATION
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField<String>(key = "BASE_URL")
        buildConfigField<String>(key = "MAPBOX_TOKEN")
        buildConfigField<String>(key = "RETABLE_API_KEY")

    }

    signingConfigs {
        create("release") {
            keyAlias = getLocalProperty(key = "keyAlias")
            keyPassword = getLocalProperty(key = "keyPassword")
            storeFile = file(getLocalProperty(key = "storeFile"))
            storePassword = getLocalProperty(key = "storePassword")
        }
    }

    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")

            configure<CrashlyticsExtension> {
                mappingFileUploadEnabled = true
            }

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions.add("mode")

    productFlavors {
        create("development") {
            dimension = "mode"
            applicationIdSuffix = ".dev"
        }

        create("production") {
            dimension = "mode"
            versionCode = ProductionConfig.CODE
            versionName = ProductionConfig.NAME
        }
    }

    compileOptions {
        sourceCompatibility = Version.JVM
        targetCompatibility = Version.JVM
    }

    kotlinOptions {
        jvmTarget = Version.JVM.toString()
        ignoreExperimentalWarnings()
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Version.COMPOSE_COMPILER
    }

    hilt {
        enableAggregatingTask = false
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    sourceSets {
        named("development") {
            res {
                srcDirs("src/development/res")
            }
        }
    }
    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }
}

kotlin {
    jvmToolchain(Version.JVM.majorVersion.toInt())
}

dependencies {
    implementation(projects.data)
    implementation(projects.presentation)
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