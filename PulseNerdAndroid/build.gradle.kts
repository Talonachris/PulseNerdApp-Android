// Root build.gradle.kts

plugins {
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.application) apply false
    kotlin("plugin.serialization") version "1.9.0"
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}