import java.util.Properties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}

allprojects {
    val properties = Properties()
    val file = rootProject.file(".env")
    if (file.exists()) {
        properties.load(file.inputStream())
        properties.forEach { key, value ->
            project.extra.set(key as String, value as String)
        }
    }
}