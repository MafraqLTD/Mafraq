package com.gateway.buildscr

import com.android.build.api.dsl.LibraryDefaultConfig
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.VariantDimension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties


fun getLocalProperty(key: String, file: String? = null): String {
    val properties = Properties()
    val defaultFiles = listOf("local.properties", "defaults.properties")
    val files = (defaultFiles + file).mapNotNull { it }

    files.forEach {
        val localProperties = File(it)
        if (localProperties.isFile) {
            runCatching {
                InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
                    if (properties[key] in listOf(null, ""))
                        properties.load(reader)
                }
            }
        }
    }

    return properties.getProperty(key).toString()
}


inline fun <reified T> VariantDimension.buildConfigField(
    key: String,
    name: String? = null
) = buildConfigField(
    type = T::class.java.name,
    name = name ?: key,
    value = getLocalProperty(key = key),
)

fun KotlinJvmOptions.ignoreExperimentalWarnings() {
    freeCompilerArgs += listOf(
        "-opt-in=com.mapbox.maps.MapboxExperimental",
        "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
        "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
        "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
        "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
        "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
        "-opt-in=androidx.compose.foundation.layout.ExperimentalLayoutApi",
        "-opt-in=androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi",
        "-opt-in=androidx.tv.material3.ExperimentalTvMaterial3Api"
    )
}

fun LibraryExtension.applyConfiguration(
    enableCompose: Boolean = false,
    defaultConfig: LibraryDefaultConfig.() -> Unit = {}
) {
    compileSdk = Config.Version.COMPILE_SDK
    buildToolsVersion = Config.Version.BUILD_TOOLS

    this.defaultConfig {
        minSdk = Config.Version.MIN_SDK
        testInstrumentationRunner = Config.ANDROID_TEST_INSTRUMENTATION
        consumerProguardFiles("consumer-rules.pro")
        defaultConfig(this)
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
        sourceCompatibility = Config.Version.JVM
        targetCompatibility = Config.Version.JVM
    }


    buildFeatures {
        compose = enableCompose
        buildConfig = true
    }

    if (enableCompose)
        composeOptions {
            kotlinCompilerExtensionVersion = Config.Version.COMPOSE_COMPILER
        }
}
