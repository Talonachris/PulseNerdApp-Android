plugins {
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    kotlin("plugin.serialization") version "1.9.0" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}