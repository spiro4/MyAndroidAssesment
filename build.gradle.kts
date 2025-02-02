/*
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlinVersion = "1.6.0" // Use the latest stable version
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        // Other classpath dependencies (e.g., Android Gradle Plugin)
        classpath("com.android.tools.build:gradle:8.1.0") // Ensure this is compatible with your setup
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false

}*/
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
}
