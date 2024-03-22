@file:Suppress("UnstableApiUsage")

import com.gateway.buildscr.Config
import com.gateway.buildscr.Config.Version
import com.gateway.buildscr.getLocalProperty
//import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension

private object LocalConfig {
    const val CODE = 1
    const val NAME = "1.0.${CODE - 1}"
}

private object ProductionConfig {
    const val CODE = 1
    const val NAME = "1.0.${CODE - 1}"
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
//    alias(libs.plugins.google.services)
//    alias(libs.plugins.google.crashlytics)
//    alias(libs.plugins.google.performance)
    id("com.google.dagger.hilt.android")
}

android {
    namespace = Config.APPLICATION_ID
    compileSdk = Version.COMPILE_SDK
    buildToolsVersion = Version.BUILD_TOOLS

    val baseUrl = "BASE_URL"
    val baseUrlDev = "BASE_URL_DEV"
    val baseUrlProd = "BASE_URL_PROD"
    val mapsApiKey = "MAPS_API_KEY"

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

        addManifestPlaceholders(
            mapOf("maps_api_key" to getLocalProperty(key = mapsApiKey))
        )
    }

    signingConfigs {
        create("release") {
            val keyPropertiesFile = "key.properties"
            keyAlias = getLocalProperty(key = "keyAlias", file = keyPropertiesFile)
            keyPassword = getLocalProperty(key = "keyPassword", file = keyPropertiesFile)
            storeFile = file(getLocalProperty(key = "storeFile", file = keyPropertiesFile))
            storePassword = getLocalProperty(key = "storePassword", file = keyPropertiesFile)
        }
    }

    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")

//            configure<CrashlyticsExtension> {
//                mappingFileUploadEnabled = true
//            }

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
            buildConfigField("String", baseUrl, getLocalProperty(key = baseUrlDev))
        }

        create("production") {
            dimension = "mode"
            buildConfigField("String", baseUrl, getLocalProperty(key = baseUrlProd))
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
        freeCompilerArgs =
            freeCompilerArgs + listOf(
                "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
                "-opt-in=com.google.accompanist.permissions.ExperimentalPermissionsApi",
            )
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Version.COMPOSE_COMPILER
    }

    hilt {
        enableAggregatingTask = true
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