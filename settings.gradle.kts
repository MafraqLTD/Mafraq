import java.util.Properties

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

val propertiesFiles = listOf("local.properties")

setProjectProperties(propertiesFiles)

fun setProjectProperties(files: List<String>) = files.forEach {
    Properties().apply {
        load(file(it).inputStream())
        forEach { key, value ->
            extra.set(key.toString(), value.toString().replace("\"", ""))
        }
    }
}


pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://jitpack.io")
        maven(url = "https://jitpack.io")
        maven(url = "https://developer.huawei.com/repo/")
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://developer.huawei.com/repo/")
        maven(url = "https://jitpack.io")
        // Mapbox Maven repository
        maven(url = "https://api.mapbox.com/downloads/v2/releases/maven") {
            val mapboxDownloadToken: String by settings
            credentials.username = "mapbox"
            credentials.password = mapboxDownloadToken
            authentication.create<BasicAuthentication>("basic")
        }

    }
}

rootProject.name = "Mafraq"
include(":app")
include(":data")
include(":presentation")
