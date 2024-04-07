plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.plugins.kotlin.gradle.get().toString())
    implementation(libs.plugins.android.gradle.get().toString())
}
