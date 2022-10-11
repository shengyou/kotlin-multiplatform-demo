plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}

group "io.kraftsman"
version "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    implementation(project(":common"))
    implementation("androidx.activity:activity-compose:1.3.0")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "io.kraftsman.android"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}