// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    dependencies {
        classpath(libs.plugins.android.gradle.get().toString())
        classpath(libs.plugins.google.services.gradle.get().toString())
        classpath(libs.plugins.google.crashlytics.gradle.get().toString())
    }

}


plugins {
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.serialization)
}
